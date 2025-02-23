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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.compose_utils.PlatformData
import io.github.compose_utils.SharedPrefs
import io.github.compose_utils.network_checker.NetworkChecker
import io.github.compose_utils.network_checker.NetworkStatus
import io.github.compose_utils.openUrl
import io.github.compose_utils.providerDispatcher
import io.github.sample.theme.AppTheme
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
internal fun App() = AppTheme {
    val scope = rememberCoroutineScope()
    val prefs = SharedPrefs()
    val platformData = PlatformData()

    val networkChecker = NetworkChecker()

    var status by remember { mutableStateOf<NetworkStatus?>(null) }

    LaunchedEffect(Unit) {
        println("get network status")
        networkChecker.networkStatusFlow.collect {
            status = it
        }
//        connectivity.statusUpdates.collect{
//            when(it){
//                is Connectivity.Status.Connected -> status = NetworkStatus(true, NetworkType.UNKNOWN)
//                Connectivity.Status.Disconnected -> status =  NetworkStatus(false , NetworkType.UNKNOWNn)
//            }
//        }

    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text("network is ${status?.isConnected}")
            Text("network type is ${status?.networkType?.name}")

            Spacer(modifier = Modifier.height(10.dp))
            // use Dispatcher
            ElevatedButton(onClick = {
                scope.launch {
                    // for make method not with the same thread main
                    withContext(providerDispatcher().io) {}
                }
            }) {
                Text("Use dispatcher")
            }

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
