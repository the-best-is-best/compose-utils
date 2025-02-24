package io.github.compose_utils_core

expect class PlatformData() {
    val name: String
    val deviceName: String
    val version: String
    val model: String
    val manufacturer: String
}