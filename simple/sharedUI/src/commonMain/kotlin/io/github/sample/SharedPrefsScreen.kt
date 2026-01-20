package io.github.sample

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.compose_utils_core.PlatformData
import io.github.compose_utils_core.SharedPrefs

@Composable
fun SharedPrefsScreen(navController: NavController) {
    val prefs = SharedPrefs()
    val platformData = PlatformData()

    var prefValue by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            // add button to nav back
            ElevatedButton(onClick = {
                navController.popBackStack()
            }) {
                Text("Go back")
            }
            Spacer(Modifier.height(10.dp))

            ElevatedButton(onClick = {
                prefs.put("test", 10)
            }) {
                Text("save in prefs")
            }
            Spacer(Modifier.height(10.dp))

            ElevatedButton(onClick = {
                val data = prefs.get("test", 0)
                println("value prefs test is $data")
                prefValue = data.toString()
            }) {
                Text("get value prefs")
            }
            Spacer(Modifier.height(10.dp))

            ElevatedButton(onClick = {
                println("name: ${platformData.name}")
                println("deviceName: ${platformData.deviceName}")
                println("model: ${platformData.model}")
                println("version: ${platformData.version}")
                println("manufacturer: ${platformData.manufacturer}")

            }) {
                Text("get platform data")
            }
            Text("value prefs is $prefValue")
        }
    }
}