package co.cyclopsapps.cleanrickandmorty
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
                apiService?.getCharacters("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYiI6InJlc3RhdXJhbnRhcHAiLCJjaWQiOiIxIn0._ri2Dghqcg-O1Yja5GCTENti3XobgcvB7-psyZLpCtA")
        } catch (exception: Exception) {
            Timber.tag("getData").e(exception)
            null
        }
    }



    suspend fun getCharacter2() = apiService?.getCharacters("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYiI6InJlc3RhdXJhbnRhcHAiLCJjaWQiOiIxIn0._ri2Dghqcg-O1Yja5GCTENti3XobgcvB7-psyZLpCtA")

}