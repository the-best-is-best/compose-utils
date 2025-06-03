package io.github.network_connectivity.network_checker

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@JsFun("() => navigator.onLine")
external fun jsIsOnline(): Boolean

@JsFun("(callback) => window.addEventListener('online', callback)")
external fun jsAddOnlineListener(callback: () -> Unit)

@JsFun("(callback) => window.addEventListener('offline', callback)")
external fun jsAddOfflineListener(callback: () -> Unit)

@JsFun("(callback) => window.removeEventListener('online', callback)")
external fun jsRemoveOnlineListener(callback: () -> Unit)

@JsFun("(callback) => window.removeEventListener('offline', callback)")
external fun jsRemoveOfflineListener(callback: () -> Unit)

actual class NetworkChecker {
    actual suspend fun getNetworkStatus(): NetworkStatus {
        return jsGetNetworkStatus()
    }

    actual val networkStatusFlow: Flow<NetworkStatus> = callbackFlow {
        val updateStatus = { trySend(jsGetNetworkStatus()) }

        jsAddOnlineListener { updateStatus() }
        jsAddOfflineListener { updateStatus() }

        updateStatus() // إرسال الحالة الحالية عند بدء التشغيل

        awaitClose {
            jsRemoveOnlineListener { updateStatus() }
            jsRemoveOfflineListener { updateStatus() }
        }
    }

    private fun jsGetNetworkStatus(): NetworkStatus {
        val isConnected = jsIsOnline() // ✅ الآن هذه الدالة موجودة
        return NetworkStatus(isConnected, if (isConnected) NetworkType.WIFI else NetworkType.NONE)
    }
}
