package io.github.compose_utils_core

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun openUrl(url: String) {
    val nsUrl = NSURL.URLWithString(url) ?: return
    UIApplication.sharedApplication.openURL(nsUrl, options = emptyMap<Any?, Any?>()) { success ->
        if (!success) {
            println("Failed to open URL: $url")

        }
    }
}

fun openUrl(nsUrl: NSURL) {
    UIApplication.sharedApplication.openURL(nsUrl, options = emptyMap<Any?, Any?>()) { success ->
        if (!success) {
            println("Failed to open URL: $nsUrl")

        }
    }
}