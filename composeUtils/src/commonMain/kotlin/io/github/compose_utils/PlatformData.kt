package io.github.compose_utils

expect class PlatformData() {
    val name: String
    val deviceName: String
    val version: String
    val model: String
    val manufacturer: String
}