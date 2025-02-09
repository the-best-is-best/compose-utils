package io.github.compose_utils

import android.content.Context

actual class SharedPrefs actual constructor() {
    private val preferenceName = "sharedPrefs"

    private val context = AndroidUtils.getActivity()


    actual fun put(key: String, value: Int) {

        context.getSpEditor().putInt(key, value).apply()

    }

    actual fun put(key: String, value: String) {

        context.getSpEditor().putString(key, value).apply()

    }

    actual fun put(key: String, value: Boolean) {

        context.getSpEditor().putBoolean(key, value).apply()

    }

    actual fun get(key: String, default: Int?): Int {
        return context.getSp().getInt(key, default ?: 0)


    }

    actual fun get(key: String): String? {

        return context.getSp().getString(key, null)
    }

    actual fun get(key: String, default: Boolean?): Boolean {

        return context.getSp().getBoolean(key, default ?: false)
    }

    actual fun remove(key: String) {

        context.getSpEditor().remove(key).apply()
    }

    private fun Context.getSp() = getSharedPreferences(preferenceName, 0)

    private fun Context.getSpEditor() = getSp().edit()

}