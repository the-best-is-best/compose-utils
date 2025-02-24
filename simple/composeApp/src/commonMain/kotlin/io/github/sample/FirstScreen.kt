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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FirstScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("First screen")
        Spacer(Modifier.height(10.dp))
        ElevatedButton(onClick = {
            navController.navigate("/prefs")
        }) {
            Text("prefs")
        }
        Spacer(Modifier.height(10.dp))
        ElevatedButton(onClick = {
            navController.navigate("/network")
        }) {
            Text("network")
        }
        Spacer(Modifier.height(10.dp))
        ElevatedButton(onClick = {
            navController.navigate("/testBack/true")
        }) {
            Text("test back")

        }
    }
}