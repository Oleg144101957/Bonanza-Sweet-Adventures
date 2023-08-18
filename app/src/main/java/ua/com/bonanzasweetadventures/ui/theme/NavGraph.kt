package ua.com.bonanzasweetadventures.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun NavGraph() {

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = Screens.Loading.target){
        composable(route = Screens.Loading.target){
            ScreenLoading(navHostController)
        }

        composable(route = Screens.Menu.target){
            ScreenMenu(navHostController)
        }

        composable(route = Screens.Bonanza.target){
            ScreenBonanza(navHostController)
        }

        composable(route = Screens.Help.target){
            ScreenHelp()
        }
    }
}