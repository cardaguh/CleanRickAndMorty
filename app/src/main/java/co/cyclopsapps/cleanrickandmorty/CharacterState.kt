package co.cyclopsapps.cleanrickandmorty

/**
 * Created by Carlos Daniel Agudelo on 17/01/2021.
 */
sealed class CharacterState {
    class ShowRestaurantData(
        var response: CharacterResponse
    ) : CharacterState()
}