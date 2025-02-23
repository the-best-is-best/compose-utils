package io.github.compose_utils.network_checker

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

actual class NetworkChecker {
    actual suspend fun getNetworkStatus(): NetworkStatus {
        return jsGetNetworkStatus()
    }

    actual val networkStatusFlow: Flow<NetworkStatus> = callbackFlow {
        val updateStatus = {
            trySend(jsGetNetworkStatus())
        }

        js("window.addEventListener('online', updateStatus)")
        js("window.addEventListener('offline', updateStatus)")

        updateStatus() // إرسال الحالة الحالية عند بدء التشغيل

        awaitClose {
            js("window.removeEventListener('online', updateStatus)")
            js("window.removeEventListener('offline', updateStatus)")
        }
    }

    private fun jsGetNetworkStatus(): NetworkStatus {
        val isConnected = js("navigator.onLine") as Boolean
        return NetworkStatus(isConnected, if (isConnected) NetworkType.WIFI else NetworkType.NONE)
    }
}
