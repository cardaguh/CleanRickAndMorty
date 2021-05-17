package co.cyclopsapps.cleanrickandmorty
import co.cyclopsapps.cleanrickandmorty.character.list.model.CharacterResponse
import co.cyclopsapps.cleanrickandmorty.services.RetrofitClient
import co.cyclopsapps.cleanrickandmorty.services.WebService
import retrofit2.Response
import timber.log.Timber


class CharacterRepository {

    private var apiService: WebService? = null

    init {
        apiService = RetrofitClient.getClient?.create(WebService::class.java)
    }

    /**
     *
     */
    suspend fun getCharacter(): Response<CharacterResponse>? {
        return try {
                apiService?.getCharacters()
        } catch (exception: Exception) {
            Timber.tag("getData").e(exception)
            null
        }
    }


    //OTRA FORMA
    suspend fun getCharacter2() = apiService?.getCharacters()

}