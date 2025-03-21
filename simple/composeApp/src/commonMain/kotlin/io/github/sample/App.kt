package io.github.sample

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.sample.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    val navController = rememberNavController()
    Scaffold {


        NavHost(navController = navController, startDestination = "/") {
            composable("/") {
                FirstScreen(navController)

            }
            composable("/prefs") {
                SharedPrefsScreen(navController)
            }
            composable("/network") {
                NetworkConnectivityScreen(navController)
            }
            composable("/testBack/{handleBack}") { backStackEntry ->
                SecondScreen(navController)
            }
        }
    }

}
