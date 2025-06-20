package io.github.compose_utils_core

import kotlin.system.exitProcess

actual fun closeApp() {
    exitProcess(0)
}