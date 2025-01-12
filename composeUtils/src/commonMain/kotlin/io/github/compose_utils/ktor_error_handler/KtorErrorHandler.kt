package io.github.compose_utils.ktor_error_handler

import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.JsonConvertException
import kotlinx.serialization.SerializationException

class KtorErrorHandler {
    companion object {
        internal var jsonConvertError = "Error please contact with support"
        internal var timeOutError = "Request time out, try again later"
        internal var notFound = "The requested resource was not found."
        internal var unAuthorized = "Please re login again"
        internal var badRequest = "Bad request. try again later"
        internal var forbiddenRequest = "Forbidden request. try again later"
        internal var internalServerError = "Something went wrong on the server side."
        internal var defaultError = "An unknown error occurred."
        internal var noInternetConnection = "No internet connection."
        internal var unknownError = "An unknown error occurred."
        internal var badGateway = "Bad Gateway."
        internal var gatewayTimeout = "Gateway Timeout."
        internal var serviceUnavailable = "Service unavailable."
        internal var conflict = "Conflict."
        internal var unsupportedMediaType = "Unsupported media type."
        internal var notImplementedError = "Not implemented."
        internal var gone = "The requested resource is no longer available."
        internal var upgradeRequired = "Upgrade required."
        internal var tooManyRequest = "Too many requests."
        internal var requestUrlTooLong = "Request URI too long."

    }

    fun init(
        jsonConvertError: String? = null,
        unAuthorized: String? = null,
        timeOutError: String? = null,
        notFound: String? = null,
        badRequest: String? = null,
        forbiddenRequest: String? = null,
        internalServerError: String? = null,
        defaultError: String?,
        noInternetConnection: String? = null,
        unknownError: String? = null,
        badGateway: String? = null,
        gatewayTimeout: String? = null,
        serviceUnavailable:String? = null,
        conflict:String? = null,
        unsupportedMediaType:String? = null,
        notImplementedError:String? = null,
        gone:String? = null,
        upgradeRequired:String? = null,
        tooManyRequest:String? = null,
        requestUrlTooLong:String? = null
    ) {
        Companion.jsonConvertError = jsonConvertError ?: Companion.jsonConvertError
        Companion.timeOutError = timeOutError ?: Companion.timeOutError
        Companion.notFound = notFound ?: Companion.notFound
        Companion.badRequest = badRequest ?: Companion.badRequest
        Companion.forbiddenRequest = forbiddenRequest ?: Companion.forbiddenRequest
        Companion.internalServerError =
            internalServerError ?: Companion.internalServerError
        Companion.defaultError = defaultError ?: Companion.defaultError
        Companion.noInternetConnection =
            noInternetConnection ?: Companion.noInternetConnection
        Companion.unknownError = unknownError ?: Companion.unknownError
        Companion.unAuthorized = unAuthorized ?: Companion.unAuthorized
        Companion.badGateway = badGateway ?: Companion.badGateway
        Companion.gatewayTimeout = gatewayTimeout ?: Companion.gatewayTimeout
        Companion.serviceUnavailable = serviceUnavailable ?: Companion.serviceUnavailable
        Companion.conflict = conflict ?: Companion.conflict
        Companion.unsupportedMediaType = unsupportedMediaType ?: Companion.unsupportedMediaType
        Companion.notImplementedError = notImplementedError ?: Companion.notImplementedError
        Companion.gone = gone ?: Companion.gone
        Companion.upgradeRequired = upgradeRequired ?: Companion.upgradeRequired
        Companion.tooManyRequest = tooManyRequest ?: Companion.tooManyRequest
        Companion.requestUrlTooLong = requestUrlTooLong ?: Companion.requestUrlTooLong



    }

    suspend fun handle(e: Any): Failure {
        when (e) {
            is ClientRequestException -> {
                val statusCode = e.response.status.value
                val message = when (statusCode) {
                    HttpStatusCode.NotFound.value -> notFound
                    HttpStatusCode.Unauthorized.value -> unAuthorized
                    HttpStatusCode.InternalServerError.value -> internalServerError
                    HttpStatusCode.BadRequest.value -> badRequest
                    HttpStatusCode.Forbidden.value -> forbiddenRequest
                    HttpStatusCode.BadGateway.value -> badGateway
                    HttpStatusCode.GatewayTimeout.value -> gatewayTimeout
                    HttpStatusCode.ServiceUnavailable.value -> serviceUnavailable
                    HttpStatusCode.RequestTimeout.value -> timeOutError
                    HttpStatusCode.Conflict.value -> conflict
                    HttpStatusCode.UnsupportedMediaType.value -> unsupportedMediaType
                    HttpStatusCode.NotImplemented.value -> notImplementedError
                    HttpStatusCode.Gone.value -> gone
                    HttpStatusCode.UpgradeRequired.value -> upgradeRequired
                    HttpStatusCode.TooManyRequests.value -> tooManyRequest
                    HttpStatusCode.RequestURITooLong.value -> requestUrlTooLong


                    else -> defaultError
                }
                val body = e.response.body<Any>()
                return Failure(
                    statusCode = HttpStatusCode.fromValue(statusCode).value,
                    messageError = message,
                    data = body

                )
            }

            is SerializationException, is JsonConvertException -> {
                return Failure(
                    messageError = jsonConvertError,
                    statusCode = 1
                )

            }


            is kotlinx.io.IOException, is Throwable -> {
                return Failure(
                    messageError = unknownError,
                    statusCode = 1
                )

            }
        }
        return Failure(messageError = unknownError, statusCode = 1)
    }
}