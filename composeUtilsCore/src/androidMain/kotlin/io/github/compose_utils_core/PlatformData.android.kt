package io.github.compose_utils_core

import android.os.Build

actual class PlatformData {
    actual val name: String
        get() = "android"

    actual val deviceName: String
        get() = "android"

    actual val version: String
        get() = "${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})"

    actual val model: String
        get() = Build.MODEL

    actual val manufacturer: String
        get() = Build.MANUFACTURER

}