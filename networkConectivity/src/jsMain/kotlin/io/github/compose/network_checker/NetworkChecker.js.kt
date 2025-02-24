package io.github.compose.network_checker

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

actual class NetworkChecker {
    private val _networkStatusFlow = MutableStateFlow(getJsNetworkStatus())
    private val networkStatusStatusFlow: StateFlow<NetworkStatus> = _networkStatusFlow.asStateFlow()

    init {
        val onlineListener: () -> Unit = { _networkStatusFlow.value = getJsNetworkStatus() }
        val offlineListener: () -> Unit = { _networkStatusFlow.value = getJsNetworkStatus() }

        val jsOnlineListener = onlineListener.unsafeCast<() -> Unit>()
        val jsOfflineListener = offlineListener.unsafeCast<() -> Unit>()

        js("window.addEventListener('online', jsOnlineListener)")
        js("window.addEventListener('offline', jsOfflineListener)")
    }

    actual suspend fun getNetworkStatus(): NetworkStatus {
        return getJsNetworkStatus()
    }

    private fun getJsNetworkStatus(): NetworkStatus {
        val isConnected = js("navigator.onLine") as Boolean
        return NetworkStatus(isConnected, NetworkType.UNKNOWN) // لا يوجد دعم رسمي لنوع الشبكة
    }

    actual val networkStatusFlow: Flow<NetworkStatus> = networkStatusStatusFlow
}
