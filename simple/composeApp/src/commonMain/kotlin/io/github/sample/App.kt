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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.compose_utils.PlatformData
import io.github.compose_utils.SharedPrefs
import io.github.compose_utils.openUrl
import io.github.sample.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    val prefs = SharedPrefs()
    val platformData = PlatformData()




    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {


            Spacer(Modifier.height(10.dp))

            ElevatedButton(onClick = {
                openUrl("https://maps.app.goo.gl/qnPYcxLP9Y9Y74od8")
            }) {
                Text("go to google map")
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
        }
    }
}
