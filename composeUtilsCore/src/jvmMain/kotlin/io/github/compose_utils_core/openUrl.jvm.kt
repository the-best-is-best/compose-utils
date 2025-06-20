package io.github.compose_utils_core

import java.awt.Desktop
import java.net.URI

actual fun openUrl(url: String) {
    val uri = URI.create(url)
    Desktop.getDesktop().browse(uri)
}