package io.github.compose_utils.network_checker

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

actual class NetworkChecker {
    actual suspend fun getNetworkStatus(): NetworkStatus {
        return jsGetNetworkStatus()
    }

    actual val networkStatusFlow: Flow<NetworkStatus> = callbackFlow {
        fun updateStatus() {
            trySend(jsGetNetworkStatus())
        }

        // تحديث الحالة عند الاتصال أو فقدانه
        js("window.addEventListener('online', updateStatus)")
        js("window.addEventListener('offline', updateStatus)")

        // إرسال الحالة المبدئية
        updateStatus()

        awaitClose {
            js("window.removeEventListener('online', updateStatus)")
            js("window.removeEventListener('offline', updateStatus)")
        }
    }

    private fun jsGetNetworkStatus(): NetworkStatus {
        val connection =
            js("navigator.connection || navigator.mozConnection || navigator.webkitConnection")
        val isConnected = js("navigator.onLine") as Boolean

        val networkType = when (connection?.type) {
            "wifi" -> NetworkType.WIFI
            "cellular" -> NetworkType.MOBILE
            else -> NetworkType.NONE
        }

        return NetworkStatus(isConnected, networkType)
    }
}
