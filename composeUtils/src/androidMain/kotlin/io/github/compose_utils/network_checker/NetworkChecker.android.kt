package io.github.compose_utils.network_checker

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import io.github.compose_utils.AndroidUtils
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

actual class NetworkChecker {
    actual suspend fun getNetworkStatus(): NetworkStatus {
        return androidGetNetworkStatus()
    }

    actual val networkStatusFlow: Flow<NetworkStatus> = callbackFlow {
        val connectivityManager = AndroidUtils.getActivity()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: android.net.Network) {
                trySend(androidGetNetworkStatus())
            }

            override fun onLost(network: android.net.Network) {
                trySend(NetworkStatus(false, NetworkType.NONE))
            }

            override fun onCapabilitiesChanged(
                network: android.net.Network,
                capabilities: NetworkCapabilities
            ) {
                trySend(androidGetNetworkStatus())
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(callback)
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            trySend(
                if (networkInfo != null && networkInfo.isConnected) androidGetNetworkStatus() else NetworkStatus(
                    false,
                    NetworkType.NONE
                )
            )
        }

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }

    private fun androidGetNetworkStatus(): NetworkStatus {
        val connectivityManager = AndroidUtils.getActivity()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkType = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network =
                connectivityManager.activeNetwork ?: return NetworkStatus(false, NetworkType.NONE)
            val capabilities =
                connectivityManager.getNetworkCapabilities(network) ?: return NetworkStatus(
                    false,
                    NetworkType.NONE
                )

            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> NetworkType.WIFI
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> NetworkType.CELLULAR
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> NetworkType.ETHERNET
                else -> NetworkType.NONE
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            when (networkInfo?.type) {
                ConnectivityManager.TYPE_WIFI -> NetworkType.WIFI
                ConnectivityManager.TYPE_MOBILE -> NetworkType.CELLULAR
                else -> NetworkType.NONE
            }
        }

        return NetworkStatus(networkType != NetworkType.NONE, networkType)
    }
}
