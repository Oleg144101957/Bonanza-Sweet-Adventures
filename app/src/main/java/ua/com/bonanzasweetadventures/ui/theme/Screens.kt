package ua.com.bonanzasweetadventures.ui.theme

sealed class Screens(val target: String){
    object Loading : Screens("loading")
    object Menu : Screens("menu")
    object Bonanza : Screens("bonanza")
    object Help : Screens("help")
}
