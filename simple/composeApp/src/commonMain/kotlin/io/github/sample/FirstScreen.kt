package io.github.sample

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.compose.rememberNavController

@Composable
fun FirstScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
      ElevatedButton(onClick = {
          navController.navigate("/secondScreen")

      }) {
          Text("Go to second screen")
      }
    }
}