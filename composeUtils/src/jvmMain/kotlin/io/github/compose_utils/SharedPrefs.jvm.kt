package io.github.compose_utils

import java.util.prefs.Preferences

actual class SharedPrefs actual constructor() {
    actual fun put(key: String, value: Int) {
        val prefs: Preferences = Preferences.userRoot().node(this.javaClass.name)
        prefs.putInt(key, value)
    }

    actual fun put(key: String, value: String) {
        val prefs: Preferences = Preferences.userRoot().node(this.javaClass.name)
        prefs.put(key, value)
    }

    actual fun put(key: String, value: Boolean) {
        val prefs: Preferences = Preferences.userRoot().node(this.javaClass.name)
        prefs.putBoolean(key, value)
    }

    actual fun get(key: String, default: Int?): Int {
        val prefs: Preferences = Preferences.userRoot().node(this.javaClass.name)
        return prefs.getInt(key, default ?: 0)

    }

    actual fun get(key: String): String? {
        val prefs: Preferences = Preferences.userRoot().node(this.javaClass.name)
        return prefs.get(key, null)
    }

    actual fun get(key: String, default: Boolean?): Boolean {
        val prefs: Preferences = Preferences.userRoot().node(this.javaClass.name)
        return prefs.getBoolean(key, default ?: false)
    }

    actual fun remove(key: String) {
        val prefs: Preferences = Preferences.userRoot().node(this.javaClass.name)
        prefs.remove(key)
    }

}