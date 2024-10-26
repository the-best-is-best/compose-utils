package io.github.compose_utils

import kotlinx.browser.localStorage

actual class SharedPrefs actual constructor() {
    actual fun putInt(key: String, value: Int) {
        localStorage.setItem(key, value.toString())

    }

    actual fun putString(key: String, value: String) {
        localStorage.setItem(key, value)
    }

    actual fun putBool(key: String, value: Boolean) {
        localStorage.setItem(key, value.toString())
    }

    actual fun getInt(key: String, default: Int?): Int {
        return localStorage.getItem(key)?.toInt() ?: 0
    }

    actual fun getString(key: String): String? {
        return localStorage.getItem(key)
    }

    actual fun getBool(key: String, default: Boolean?): Boolean {
        val value = localStorage.getItem(key)
        return value?.toBoolean() ?: false
    }

    actual fun remove(key: String) {
        localStorage.removeItem(key)
    }

}