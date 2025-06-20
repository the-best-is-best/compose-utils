package io.github.compose_utils_core

import  platform.AppKit.NSWorkspace
import platform.Foundation.NSURL

actual fun openUrl(url: String) {
    val nsUrl = NSURL.URLWithString(url)
    if (nsUrl != null) {
        val success = NSWorkspace.sharedWorkspace.openURL(nsUrl)
        if (!success) {
            println("Failed to open URL: $url")
        }
    }
}

fun openUrl(nsUrl: NSURL) {
    val success = NSWorkspace.sharedWorkspace.openURL(nsUrl)
    if (!success) {
        println("Failed to open URL: $nsUrl")
    }
}

