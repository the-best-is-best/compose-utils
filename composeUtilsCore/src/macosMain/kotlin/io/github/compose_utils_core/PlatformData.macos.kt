package io.github.compose_utils_core

import platform.Foundation.NSProcessInfo

actual class PlatformData {
    actual val platform: Platform
        get() = Platform.MacOS

    actual val name: String
        get() = "macOS"

    actual val deviceName: String
        get() = NSProcessInfo.processInfo.hostName

    actual val version: String
        get() = NSProcessInfo.processInfo.operatingSystemVersionString

    actual val model: String
        get() = "Mac" // مفيش API مباشرة للموديل زي iPhone/iPad

    actual val manufacturer: String
        get() = "Apple"
}
