package co.cyclopsapps.cleanrickandmorty.services

import co.cyclopsapps.cleanrickandmorty.character.list.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebService {

    //Lo que hace falta en la url
    @GET("character") // Aquí va el end-point

    suspend fun getCharacters()
            : Response<CharacterResponse>
}