package co.cyclopsapps.cleanrickandmorty

import com.google.gson.annotations.SerializedName

/**
 * Created by Carlos Daniel Agudelo on 17/01/2021.
 */
data class CharacterResponse (
    val company: Character,
    val category: MutableList<CharacterData>? = mutableListOf()
)

data class Character (
    @SerializedName("id")
    val restaurantId: String = "",

    //Esto lo puse en el caso que tuvieses
    // un campo con nombre extraño
    @SerializedName("img")

    //Nombre al que se accede como atributo
    val imagen: String = "",


    val name: String = "",

    //Esto lo puse en el caso que tuvieses
    // un campo con nombre extraño
    @SerializedName("calle")

    //Nombre al que se accede como atributo
    val description: String = ""
)


data class CharacterData(
    val id: Int,
    val img: String = "",
    val name: String = ""
)