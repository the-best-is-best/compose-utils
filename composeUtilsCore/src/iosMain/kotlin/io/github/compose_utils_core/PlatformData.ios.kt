package io.github.compose_utils_core

import platform.UIKit.UIDevice

actual class PlatformData {
    actual val platform: Platform
        get() = Platform.IOS

    actual val name: String
        get() = "iOS"

    actual val deviceName: String
        get() = UIDevice.currentDevice.name

    actual val version: String
        get() = UIDevice.currentDevice.systemVersion

    actual val model: String
        get() = UIDevice.currentDevice.model

    actual val manufacturer: String
        get() = "Apple"
}
