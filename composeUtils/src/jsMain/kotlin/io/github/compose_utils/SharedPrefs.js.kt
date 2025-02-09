package io.github.compose_utils

import kotlinx.browser.localStorage

actual class SharedPrefs actual constructor() {
    actual fun put(key: String, value: Int) {
        localStorage.setItem(key, value.toString())

    }

    actual fun put(key: String, value: String) {
        localStorage.setItem(key, value)
    }

    actual fun put(key: String, value: Boolean) {
        localStorage.setItem(key, value.toString())
    }

    actual fun get(key: String, default: Int?): Int {
        return localStorage.getItem(key)?.toInt() ?: 0
    }

    actual fun get(key: String): String? {
        return localStorage.getItem(key)
    }

    actual fun get(key: String, default: Boolean?): Boolean {
        val value = localStorage.getItem(key)
        return value?.toBoolean() ?: false
    }

    actual fun remove(key: String) {
        localStorage.removeItem(key)
    }

}