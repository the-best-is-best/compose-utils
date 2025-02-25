# NetworkConnectivity

`NetworkConnectivity` is a utility library designed to streamline network status detection in **Compose Multiplatform (CMP)** projects. It provides real-time updates on the network connection state across different platforms.

## Download

[![Maven Central](https://img.shields.io/maven-central/v/io.github.the-best-is-best/compose-utils-network-connectivity)](https://central.sonatype.com/artifact/io.github.the-best-is-best/compose-utils-network-connectivity)

Compose Utils Network Connectivity is available on `mavenCentral()`.

## Features

- **Detect Network Status**: Check if the device is connected to the internet.
- **Network Type Detection**: Identify if the connection is via WiFi, Cellular, Ethernet, etc.
- **Flow-based Updates**: Subscribe to network status changes reactively.

## Installation

Add the dependency to your Kotlin Multiplatform project:

```kotlin
dependencies {
    implementation("io.github.the-best-is-best:compose-utils-network-connectivity:<latest-version>")
}
```

## Usage

### Checking Network Status (Suspend Function)

```kotlin
suspend fun checkNetwork() {
    val checker = NetworkChecker()
    val status = checker.getNetworkStatus()
    println("Connected: ${status.isConnected}, Type: ${status.networkType}")
}
```

### Observing Network Status Changes (Flow)

```kotlin
val checker = NetworkChecker()
LaunchedEffect(Unit) {
    checker.networkStatusFlow.collect { status ->
        println("Network changed: Connected = ${status.isConnected}, Type = ${status.networkType}")
    }
}
```

## NetworkStatus Model

```kotlin
data class NetworkStatus(
    val isConnected: Boolean,
    val networkType: NetworkType
)
```

## Supported Network Types

```kotlin
enum class NetworkType {
    WIFI, CELLULAR, ETHERNET, UNKNOWN, NONE
}
```

## License

This project is licensed under the MIT License.
