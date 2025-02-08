package io.github.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.compose_utils.PlatformData
import io.github.compose_utils.SharedPrefs
import io.github.compose_utils.openUrl
import io.github.compose_utils.providerDispatcher
import io.github.sample.theme.AppTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
internal fun Navigation() = AppTheme {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "/" // Default starting screen
    ) {
        composable("/") { // Route for the HomeScreen
            FirstScreen(navController)
        }
        composable("/secondScreen") { // Route for the DetailsScreen
            SecondScreen(navController)
        }
    }
}
