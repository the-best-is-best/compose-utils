package io.github.compose_utils_core

import kotlinx.browser.window

actual class SharedPrefs actual constructor() {
    actual fun put(key: String, value: Int) {
        window.localStorage.setItem(key, value.toString())
    }

    actual fun put(key: String, value: String) {
        window.localStorage.setItem(key, value)
    }

    actual fun put(key: String, value: Boolean) {
        window.localStorage.setItem(key, value.toString())
    }

    actual fun get(key: String, default: Int?): Int {
        val value = window.localStorage.getItem(key)
        return value?.toIntOrNull() ?: default ?: 0
    }

    actual fun get(key: String): String? {
        return window.localStorage.getItem(key)
    }

    actual fun get(key: String, default: Boolean?): Boolean {
        val value = window.localStorage.getItem(key)
        return value?.toBoolean() ?: default ?: false
    }

    actual fun remove(key: String) {
        window.localStorage.removeItem(key)
    }
}
