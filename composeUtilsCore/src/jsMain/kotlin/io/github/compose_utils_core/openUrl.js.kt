package io.github.compose_utils_core

import kotlinx.browser.window

actual fun openUrl(url: String?) {
    url?.let { window.open(it) }

}