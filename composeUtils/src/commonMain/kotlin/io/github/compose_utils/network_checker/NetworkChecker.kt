package io.github.compose_utils.network_checker

import kotlinx.coroutines.flow.Flow

expect class NetworkChecker() {
    suspend fun getNetworkStatus(): NetworkStatus
    val networkStatusFlow: Flow<NetworkStatus>

}

data class NetworkStatus(
    val isConnected: Boolean,
    val networkType: NetworkType
)

enum class NetworkType {
    WIFI, CELLULAR, ETHERNET, UNKNOWN, NONE
}
