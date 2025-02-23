package io.github.compose_utils.network_checker

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.Network.nw_interface_type_cellular
import platform.Network.nw_interface_type_wifi
import platform.Network.nw_path_get_status
import platform.Network.nw_path_monitor_cancel
import platform.Network.nw_path_monitor_create
import platform.Network.nw_path_monitor_set_queue
import platform.Network.nw_path_monitor_set_update_handler
import platform.Network.nw_path_monitor_start
import platform.Network.nw_path_status_satisfied
import platform.Network.nw_path_uses_interface_type
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class NetworkChecker {


    actual suspend fun getNetworkStatus(): NetworkStatus = suspendCoroutine { continuation ->
        val monitor = nw_path_monitor_create()

        nw_path_monitor_set_update_handler(monitor) { path ->
            val isConnected = nw_path_get_status(path) == nw_path_status_satisfied
            val networkType = when {
                nw_path_uses_interface_type(path, nw_interface_type_wifi) -> NetworkType.WIFI
                nw_path_uses_interface_type(path, nw_interface_type_cellular) -> NetworkType.MOBILE
                else -> NetworkType.NONE
            }

            continuation.resume(NetworkStatus(isConnected, networkType))
        }

        nw_path_monitor_set_queue(monitor, dispatch_get_main_queue())
        nw_path_monitor_start(monitor)
    }

    actual val networkStatusFlow: Flow<NetworkStatus> = callbackFlow {
        println("Network monitoring started")

        // Create a monitor for continuous updates
        val monitor = nw_path_monitor_create()

        var lastStatus: NetworkStatus? = null  // Track the last status

        // Network status handler
        nw_path_monitor_set_update_handler(monitor) { path ->
            val isConnected = nw_path_get_status(path) == nw_path_status_satisfied
            val networkType = when {
                nw_path_uses_interface_type(path, nw_interface_type_wifi) -> NetworkType.WIFI
                nw_path_uses_interface_type(path, nw_interface_type_cellular) -> NetworkType.MOBILE
                else -> NetworkType.NONE
            }

            val currentStatus = NetworkStatus(isConnected, networkType)

            // Emit only if the status has changed
            if (lastStatus != currentStatus) {
                println("Network status changed: $isConnected, $networkType")
                trySend(currentStatus)  // Send the status update
                lastStatus = currentStatus  // Update last status
            } else {
                println("Network status is the same, no emission.")
            }
        }

        nw_path_monitor_set_queue(monitor, dispatch_get_main_queue())
        nw_path_monitor_start(monitor)

        // Keep the flow open to receive further updates
        println("Monitor started, awaiting close...")

        // Ensure the monitoring continues
        awaitClose {
            println("Monitoring stopped")
            nw_path_monitor_cancel(monitor)
            println("Monitor has been canceled")
        }
    }
}
