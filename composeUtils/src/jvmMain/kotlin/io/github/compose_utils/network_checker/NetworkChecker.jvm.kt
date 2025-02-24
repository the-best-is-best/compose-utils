package io.github.compose_utils.network_checker

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.net.NetworkInterface

actual class NetworkChecker {
    private val _networkStatusFlow = MutableStateFlow(jvmGetNetworkStatus())

    actual val networkStatusFlow: Flow<NetworkStatus> = _networkStatusFlow

    init {
        CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                _networkStatusFlow.value = jvmGetNetworkStatus()
                delay(5000) // Adjust delay if needed
            }
        }
    }

    actual suspend fun getNetworkStatus(): NetworkStatus = jvmGetNetworkStatus()

    private fun jvmGetNetworkStatus(): NetworkStatus {
        val interfaces = NetworkInterface.getNetworkInterfaces()?.toList().orEmpty()
            .filter { it.isUp && !it.isLoopback }

        val networkType = when {
            interfaces.hasInterface("en0", "Wi-Fi", "wlan") -> NetworkType.WIFI
            interfaces.hasInterface(
                "en",
                "Ethernet",
                "Local Area Connection"
            ) -> NetworkType.ETHERNET

            interfaces.hasInterface("rmnet", "pdp") -> NetworkType.CELLULAR
            else -> NetworkType.NONE
        }

        return NetworkStatus(networkType != NetworkType.NONE, networkType)
    }

    private fun List<NetworkInterface>.hasInterface(vararg keywords: String): Boolean {
        return any { iface ->
            keywords.any { keyword ->
                iface.name.contains(keyword, ignoreCase = true) || iface.displayName.contains(
                    keyword,
                    ignoreCase = true
                )
            }
        }
    }
}

