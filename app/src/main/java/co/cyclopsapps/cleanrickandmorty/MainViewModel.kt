package co.cyclopsapps.cleanrickandmorty

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel: ViewModel(), CoroutineScope {

    private val states: MutableLiveData<ScreenState<CharacterState>> = MutableLiveData()
    private val repository = CharacterRepository()

    var characterResponse: CharacterResponse? = null

    private val viewModelJob = Job()
    override val coroutineContext: CoroutineContext
        get() = viewModelJob + Dispatchers.Default

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun getState(): MutableLiveData<ScreenState<CharacterState>> {
        return states
    }


    fun getRestaurantData() {
        states.value = ScreenState.Loading
        viewModelScope.launch {
            repository.getCharacter()?.body()?.let {
                characterResponse = it
                states.value = ScreenState.Render(CharacterState.ShowRestaurantData(it))
            } ?: run {
                states.value = ScreenState.ErrorServer
            }
        }
    }

}

