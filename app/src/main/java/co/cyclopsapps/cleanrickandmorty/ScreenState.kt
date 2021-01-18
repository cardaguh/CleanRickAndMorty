package co.cyclopsapps.cleanrickandmorty

sealed class ScreenState<out T> {
    object Loading : ScreenState<Nothing>()
    object Refresh : ScreenState<Nothing>()
    object ErrorServer : ScreenState<Nothing>()
    object InternetError : ScreenState<Nothing>()
    class Render<T>(var renderState: T) : ScreenState<T>()
    class ShowErrorBody <T>(var errorBody: T) : ScreenState<T>()
}