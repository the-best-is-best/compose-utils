package io.github.compose_utils_core

actual class PlatformData {
    actual val platform: Platform
        get() = Platform.Desktop

    actual val name: String
        get() = "jvm"

    actual val deviceName: String
        get() {
            val os = System.getProperty("os.name").lowercase()
            return when {
                os.contains("win") -> "Windows"
                os.contains("mac") -> "Mac"
                os.contains("nix") || os.contains("nux") || os.contains("aix") -> "Linux"
                else -> "Unknown"
            }
        }

    actual val version: String
        get() {
            val javaVersion = System.getProperty("java.version")
            return javaVersion ?: "Unknown"
        }

    actual val model: String
        get() {
            // JVM does not typically provide model information; this can be set to "N/A".
            return "N/A"
        }

    actual val manufacturer: String
        get() {
            // JVM does not typically provide manufacturer information; this can be set to "N/A".
            return "N/A"
        }
}
