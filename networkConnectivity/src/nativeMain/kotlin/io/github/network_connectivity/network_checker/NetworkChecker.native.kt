package io.github.network_connectivity.network_checker

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.Network.nw_interface_type_cellular
import platform.Network.nw_interface_type_wifi
import platform.Network.nw_interface_type_wired
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
                nw_path_uses_interface_type(path, nw_interface_type_wired) -> NetworkType.ETHERNET
                nw_path_uses_interface_type(
                    path,
                    nw_interface_type_cellular
                ) -> NetworkType.CELLULAR

                else -> NetworkType.NONE
            }

            continuation.resume(NetworkStatus(isConnected, networkType))
            nw_path_monitor_cancel(monitor) // إيقاف المراقب بعد الحصول على النتيجة
        }

        nw_path_monitor_set_queue(monitor, dispatch_get_main_queue())
        nw_path_monitor_start(monitor)
    }

    @OptIn(DelicateCoroutinesApi::class)
    actual val networkStatusFlow: Flow<NetworkStatus> = callbackFlow {
        val monitor = nw_path_monitor_create()
        nw_path_monitor_set_queue(monitor, dispatch_get_main_queue())

        var lastStatus: NetworkStatus? = null // تتبع آخر حالة مرسلة

        nw_path_monitor_set_update_handler(monitor) { path ->
            val status = nw_path_get_status(path)
            val isConnected = status == nw_path_status_satisfied

            val networkType = when {
                nw_path_uses_interface_type(path, nw_interface_type_wifi) -> NetworkType.WIFI
                nw_path_uses_interface_type(path, nw_interface_type_wired) -> NetworkType.ETHERNET
                nw_path_uses_interface_type(
                    path,
                    nw_interface_type_cellular
                ) -> NetworkType.CELLULAR

                else -> NetworkType.NONE
            }

            val currentStatus = NetworkStatus(isConnected, networkType)

            if (lastStatus != currentStatus) { // إرسال فقط إذا تغيرت الحالة
                lastStatus = currentStatus
                println("New network status: $currentStatus")

                if (!isClosedForSend) {
                    try {
                        trySend(currentStatus).isSuccess
                    } catch (e: Exception) {
                        println("Error sending network status: ${e.message}")
                    }
                }
            }
        }

        // ✅ بدء المراقبة مباشرةً بدون تأخير
        nw_path_monitor_start(monitor)

        awaitClose {
            println("Network monitoring stopped")
            nw_path_monitor_cancel(monitor)
        }
    }
}
