package co.cyclopsapps.cleanrickandmorty

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("results")
    suspend fun getCharacters(@Query(value = "token") tokenValue: String)
            : Response<CharacterResponse>
}