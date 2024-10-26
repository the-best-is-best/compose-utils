package io.github.compose_utils

expect class SharedPrefs() {
    fun putInt(key: String, value: Int)
    fun putString(key: String, value: String)
    fun putBool(key: String, value: Boolean)
    fun getInt(key: String, default: Int?): Int
    fun getString(key: String): String?
    fun getBool(key: String, default: Boolean?): Boolean
    fun remove(key: String)

}