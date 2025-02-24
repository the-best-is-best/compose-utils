package io.github.compose_utils_core

expect class SharedPrefs() {
    fun put(key: String, value: Int)
    fun put(key: String, value: String)
    fun put(key: String, value: Boolean)
    fun get(key: String, default: Int?): Int
    fun get(key: String): String?
    fun get(key: String, default: Boolean?): Boolean
    fun remove(key: String)

}