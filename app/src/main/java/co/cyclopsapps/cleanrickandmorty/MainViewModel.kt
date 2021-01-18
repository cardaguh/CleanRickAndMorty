package co.cyclopsapps.cleanrickandmorty

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.cyclopsapps.cleanrickandmorty.character.CharacterState
import co.cyclopsapps.cleanrickandmorty.character.list.model.CharacterDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel: ViewModel(), CoroutineScope {

    private val states: MutableLiveData<ScreenState<CharacterState>> = MutableLiveData()
    private val repository = CharacterRepository()

    var characterFullList: MutableList<CharacterDataModel>? = mutableListOf()

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

    fun getCharacterData(id: Long): CharacterDataModel? {
        return characterFullList?.firstOrNull { it.id == id }
    }

    fun fetchCharacterData() {
        states.value = ScreenState.Loading
        viewModelScope.launch {
            repository.getCharacter()?.body()?.let { body ->
                characterFullList = body.results
                states.value = ScreenState.Render(CharacterState.ShowRestaurantData(body))
            } ?: run {
                states.value = ScreenState.ErrorServer
            }
        }
    }

}

// ?.let {...}     ==  != null