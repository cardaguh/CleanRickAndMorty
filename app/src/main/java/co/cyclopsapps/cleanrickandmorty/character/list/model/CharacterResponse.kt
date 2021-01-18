package co.cyclopsapps.cleanrickandmorty.character.list.model


/**
 * Created by Carlos Daniel Agudelo on 17/01/2021.
 */
data class CharacterResponse(
    val info: Pager,
    val results: MutableList<CharacterDataModel>? = mutableListOf()
)

data class Pager(
    val count: Long,
    val pages: Int,
    val next: String?,
    val prev: String?
)


data class CharacterDataModel(
    val id: Long,
    val name: String = "",
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode: MutableList<String>,
    val url: String
)


data class Origin(
    val name: String,
    val url: String
)

data class Location(
    val name: String,
    val url: String
)