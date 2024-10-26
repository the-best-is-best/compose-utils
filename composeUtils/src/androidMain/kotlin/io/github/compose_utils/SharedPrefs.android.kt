package io.github.compose_utils

import android.content.Context

actual class SharedPrefs {
    private val preferenceName = "sharedPrefs"

    private val context = AndroidUtils.getActivity()

    actual fun putInt(key: String, value: Int) {
        context.getSpEditor().putInt(key, value).apply()
    }

    actual fun putString(key: String, value: String) {
        context.getSpEditor().putString(key, value).apply()
    }

    actual fun putBool(key: String, value: Boolean) {
        context.getSpEditor().putBoolean(key, value).apply()
    }

    actual fun getInt(key: String, default: Int?): Int {
        return context.getSp().getInt(key, default ?: 0)
    }

    actual fun getString(key: String): String? {
        return context.getSp().getString(key, null)
    }

    actual fun getBool(key: String, default: Boolean?): Boolean {
        return context.getSp().getBoolean(key, default ?: false)
    }

    actual fun remove(key: String) {
        context.getSpEditor().remove(key).apply()
    }

    private fun Context.getSp() = getSharedPreferences(preferenceName, 0)

    private fun Context.getSpEditor() = getSp().edit()

}