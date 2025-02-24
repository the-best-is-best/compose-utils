package io.github.ktor_error_handler

data class Failure(
    val statusCode: Int? = null,
    val messageError: String? = null,
    val data: Any? = null,

    )