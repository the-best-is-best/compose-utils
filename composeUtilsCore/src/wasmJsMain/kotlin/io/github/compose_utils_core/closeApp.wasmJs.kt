package io.github.compose_utils_core

import kotlinx.browser.window

actual fun closeApp() {
    window.close()
}