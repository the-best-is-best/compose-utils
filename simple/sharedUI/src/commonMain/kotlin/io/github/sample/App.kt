package io.github.sample

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.compose_utils_core.PlatformData
import io.github.sample.theme.AppTheme

@Composable
fun App() = AppTheme {

    val navController = rememberNavController()
    LaunchedEffect(Unit) {
        println("platform: ${PlatformData().platform}")
    }

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
