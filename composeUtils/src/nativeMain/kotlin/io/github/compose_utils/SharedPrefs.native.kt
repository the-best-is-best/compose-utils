package io.github.compose_utils

import platform.Foundation.NSUserDefaults

actual class SharedPrefs actual constructor() {
    actual fun put(key: String, value: Int) {
        NSUserDefaults.standardUserDefaults.setInteger(value.toLong(), key)
    }

    actual fun put(key: String, value: String) {
        NSUserDefaults.standardUserDefaults.setObject(value, key)
    }

    actual fun put(key: String, value: Boolean) {
        NSUserDefaults.standardUserDefaults.setBool(value, key)
    }

    actual fun get(key: String, default: Int?): Int {
        return NSUserDefaults.standardUserDefaults.integerForKey(key).toInt()
    }

    actual fun get(key: String): String? {
        return NSUserDefaults.standardUserDefaults.stringForKey(key)
    }

    actual fun get(key: String, default: Boolean?): Boolean {
        return NSUserDefaults.standardUserDefaults.boolForKey(key)
    }

    actual fun remove(key: String) {
        NSUserDefaults.standardUserDefaults.removeSuiteNamed(key)
    }

}