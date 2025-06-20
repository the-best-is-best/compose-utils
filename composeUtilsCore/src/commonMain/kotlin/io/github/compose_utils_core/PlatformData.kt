package io.github.compose_utils_core

enum class Platform {
    Android,
    IOS,
    Web,
    Desktop,
    MacOS,
}

expect class PlatformData() {
    val platform: Platform
    val name: String
    val deviceName: String
    val version: String
    val model: String
    val manufacturer: String
}