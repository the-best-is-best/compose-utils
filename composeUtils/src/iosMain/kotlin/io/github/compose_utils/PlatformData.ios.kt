package io.github.compose_utils

import platform.UIKit.UIDevice

actual class PlatformData {
    actual val name: String
        get() = "ios"

    actual val deviceName: String
        get() = UIDevice.currentDevice.name

    actual val version: String
        get() = UIDevice.currentDevice.systemVersion

    actual val model: String
        get() = UIDevice.currentDevice.model

    actual val manufacturer: String
        get() = "Apple"


}