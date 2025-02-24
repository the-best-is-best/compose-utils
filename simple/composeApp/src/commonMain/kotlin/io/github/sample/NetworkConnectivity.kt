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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.compose.network_checker.NetworkChecker
import io.github.compose.network_checker.NetworkStatus
import kotlinx.coroutines.launch

@Composable
fun NetworkConnectivityScreen(navController: NavController) {
    val networkChecker = NetworkChecker()
    val scope = rememberCoroutineScope()

    var status by remember { mutableStateOf<NetworkStatus?>(null) }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

            ElevatedButton(onClick = {
                navController.popBackStack()
            }) {
                Text("Go back")
            }
            Spacer(Modifier.height(10.dp))

            Text("network is ${status?.isConnected}")
            Text("network type is ${status?.networkType?.name}")

            Spacer(modifier = Modifier.height(10.dp))
            // use Dispatcher
            ElevatedButton(onClick = {
                scope.launch {
                    status = networkChecker.getNetworkStatus()
                }
            }) {
                Text("get internet status")
            }
            Spacer(modifier = Modifier.height(10.dp))
            ElevatedButton(onClick = {
                scope.launch {
                    networkChecker.networkStatusFlow.collect {
                        status = it
                    }
                }
            }) {
                Text("stream internet status")
            }
        }

    }
}