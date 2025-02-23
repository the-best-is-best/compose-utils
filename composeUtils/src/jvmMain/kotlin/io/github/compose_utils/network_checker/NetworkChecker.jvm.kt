package io.github.compose_utils.network_checker

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.isActive
import java.net.NetworkInterface

actual class NetworkChecker {
    actual suspend fun getNetworkStatus(): NetworkStatus {
        return jvmGetNetworkStatus()
    }

    actual val networkStatusFlow: Flow<NetworkStatus> = callbackFlow {
        while (isActive) {
            trySend(jvmGetNetworkStatus()) // تحديث الحالة وإرسالها
            delay(5000) // تحديث كل 5 ثوانٍ (يمكن تغييره حسب الحاجة)
        }
        awaitClose { /* لا يوجد شيء للإغلاق هنا */ }
    }

    private fun jvmGetNetworkStatus(): NetworkStatus {
        val interfaces = NetworkInterface.getNetworkInterfaces().toList()
            .filter {
                it.isUp && !it.isLoopback && !it.name.startsWith("utun") && it.name !in listOf(
                    "llw0",
                    "awdl0"
                )
            } // استبعاد الشبكات غير المفيدة

        val networkType = when {
            interfaces.any {
                it.name == "en0" || it.displayName.contains(
                    "Wi-Fi",
                    ignoreCase = true
                ) || it.name.startsWith("wlan")
            } -> NetworkType.WIFI

            interfaces.any {
                it.name.startsWith("en") || it.displayName.contains(
                    "Ethernet",
                    ignoreCase = true
                ) || it.displayName.contains("Local Area Connection", ignoreCase = true)
            } -> NetworkType.ETHERNET

            interfaces.any { it.name.startsWith("rmnet") || it.name.startsWith("pdp") } -> NetworkType.MOBILE
            else -> NetworkType.NONE
        }

        val isConnected = networkType != NetworkType.NONE
        return NetworkStatus(isConnected, networkType)
    }
}
