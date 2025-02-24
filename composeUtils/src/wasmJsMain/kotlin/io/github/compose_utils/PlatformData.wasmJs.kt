package io.github.compose_utils

import kotlinx.browser.window

actual class PlatformData {
    private val userAgent = window.navigator.userAgent


    actual val name: String
        get() = "js"
    actual val deviceName: String
        get() {
            return when {
                userAgent.contains("Chrome") -> "Chrome"
                userAgent.contains("Firefox") -> "Firefox"
                userAgent.contains("Safari") && !userAgent.contains("Chrome") -> "Safari"
                userAgent.contains("Edge") -> "Edge"
                userAgent.contains("Opera") || userAgent.contains("OPR") -> "Opera"
                else -> "Unknown"
            }
        }
    actual val version: String
        get() {
            return when {
                userAgent.contains("Chrome") -> userAgent.substringAfter("Chrome/")
                    .substringBefore(" ")

                userAgent.contains("Firefox") -> userAgent.substringAfter("Firefox/")
                    .substringBefore(" ")

                userAgent.contains("Safari") && !userAgent.contains("Chrome") -> userAgent.substringAfter(
                    "Version/"
                ).substringBefore(" ")

                userAgent.contains("Edge") -> userAgent.substringAfter("Edge/").substringBefore(" ")
                userAgent.contains("Opera") || userAgent.contains("OPR") -> userAgent.substringAfter(
                    "OPR/"
                ).substringBefore(" ")

                else -> "Unknown"
            }
        }
    actual val model: String
        get() = "N/A"
    actual val manufacturer: String
        get() = "N/A"
}