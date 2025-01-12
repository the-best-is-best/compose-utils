package io.github.compose_utils.ktor_error_handler

data class Failure(
    val statusCode: Int? = null,
    val messageError:String? = null,
    val data: Any? = null,

)