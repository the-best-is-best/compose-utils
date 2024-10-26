package io.github.compose_utils

import kotlinx.browser.window

actual class SharedPrefs actual constructor() {
    actual fun putInt(key: String, value: Int) {
        window.localStorage.setItem(key, value.toString())
    }

    actual fun putString(key: String, value: String) {
        window.localStorage.setItem(key, value)
    }

    actual fun putBool(key: String, value: Boolean) {
        window.localStorage.setItem(key, value.toString())
    }

    actual fun getInt(key: String, default: Int?): Int {
        val value = window.localStorage.getItem(key)
        return value?.toIntOrNull() ?: default ?: 0
    }

    actual fun getString(key: String): String? {
        return window.localStorage.getItem(key)
    }

    actual fun getBool(key: String, default: Boolean?): Boolean {
        val value = window.localStorage.getItem(key)
        return value?.toBoolean() ?: default ?: false
    }

    actual fun remove(key: String) {
        window.localStorage.removeItem(key)
    }
}
