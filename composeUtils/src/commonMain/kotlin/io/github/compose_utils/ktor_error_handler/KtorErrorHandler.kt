package io.github.compose_utils.ktor_error_handler

import androidx.compose.runtime.Composable
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.JsonConvertException
import kotlinx.io.IOException
import kotlinx.serialization.SerializationException
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

object KtorErrorHandler {
    var jsonConvertError = "Error please contact support"
    var timeOutError = "Request time out, try again later"
    var notFound = "The requested resource was not found."
    var unAuthorized = "Please re-login again"
    var badRequest = "Bad request. Try again later"
    var forbiddenRequest = "Forbidden request. Try again later"
    var internalServerError = "Something went wrong on the server side."
    var defaultError = "An unknown error occurred."
    var noInternetConnection = "No internet connection."
    var unknownError = "An unknown error occurred."
    var badGateway = "Bad Gateway."
    var gatewayTimeout = "Gateway Timeout."
    var serviceUnavailable = "Service unavailable."
    var conflict = "Conflict."
    var unsupportedMediaType = "Unsupported media type."
    var notImplementedError = "Not implemented."
    var gone = "The requested resource is no longer available."
    var upgradeRequired = "Upgrade required."
    var tooManyRequests = "Too many requests."
    var requestUrlTooLong = "Request URI too long."

    @Composable
    fun init(
        jsonConvertError: StringResource? = null,
        unAuthorized: StringResource? = null,
        timeOutError: StringResource? = null,
        notFound: StringResource? = null,
        badRequest: StringResource? = null,
        forbiddenRequest: StringResource? = null,
        internalServerError: StringResource? = null,
        defaultError: StringResource? = null,
        noInternetConnection: StringResource? = null,
        unknownError: StringResource? = null,
        badGateway: StringResource? = null,
        gatewayTimeout: StringResource? = null,
        serviceUnavailable: StringResource? = null,
        conflict: StringResource? = null,
        unsupportedMediaType: StringResource? = null,
        notImplementedError: StringResource? = null,
        gone: StringResource? = null,
        upgradeRequired: StringResource? = null,
        tooManyRequests: StringResource? = null,
        requestUrlTooLong: StringResource? = null
    ) {
        jsonConvertError?.let { this.jsonConvertError = stringResource(it) }
        timeOutError?.let { this.timeOutError = stringResource(it) }
        notFound?.let { this.notFound = stringResource(it) }
        badRequest?.let { this.badRequest = stringResource(it) }
        forbiddenRequest?.let { this.forbiddenRequest = stringResource(it) }
        internalServerError?.let { this.internalServerError = stringResource(it) }
        defaultError?.let { this.defaultError = stringResource(it) }
        noInternetConnection?.let { this.noInternetConnection = stringResource(it) }
        unknownError?.let { this.unknownError = stringResource(it) }
        unAuthorized?.let { this.unAuthorized = stringResource(it) }
        badGateway?.let { this.badGateway = stringResource(it) }
        gatewayTimeout?.let { this.gatewayTimeout = stringResource(it) }
        serviceUnavailable?.let { this.serviceUnavailable = stringResource(it) }
        conflict?.let { this.conflict = stringResource(it) }
        unsupportedMediaType?.let { this.unsupportedMediaType = stringResource(it) }
        notImplementedError?.let { this.notImplementedError = stringResource(it) }
        gone?.let { this.gone = stringResource(it) }
        upgradeRequired?.let { this.upgradeRequired = stringResource(it) }
        tooManyRequests?.let { this.tooManyRequests = stringResource(it) }
        requestUrlTooLong?.let { this.requestUrlTooLong = stringResource(it) }
    }

    suspend fun handle(e: Exception): Failure {
        return when (e) {
            is ClientRequestException -> {
                val statusCode = e.response.status
                val message = when (statusCode) {
                    HttpStatusCode.NotFound -> notFound
                    HttpStatusCode.Unauthorized -> unAuthorized
                    HttpStatusCode.InternalServerError -> internalServerError
                    HttpStatusCode.BadRequest -> badRequest
                    HttpStatusCode.Forbidden -> forbiddenRequest
                    HttpStatusCode.BadGateway -> badGateway
                    HttpStatusCode.GatewayTimeout -> gatewayTimeout
                    HttpStatusCode.ServiceUnavailable -> serviceUnavailable
                    HttpStatusCode.RequestTimeout -> timeOutError
                    HttpStatusCode.Conflict -> conflict
                    HttpStatusCode.UnsupportedMediaType -> unsupportedMediaType
                    HttpStatusCode.NotImplemented -> notImplementedError
                    HttpStatusCode.Gone -> gone
                    HttpStatusCode.UpgradeRequired -> upgradeRequired
                    HttpStatusCode.TooManyRequests -> tooManyRequests
                    HttpStatusCode.RequestURITooLong -> requestUrlTooLong
                    else -> defaultError
                }
                val body = e.response.body<Any?>()
                Failure(statusCode.value, message, body)
            }

            is SerializationException, is JsonConvertException -> {
                Failure(statusCode = 1, messageError = jsonConvertError)
            }

            is IOException -> {
                Failure(statusCode = 0, messageError = noInternetConnection)
            }

            else -> Failure(statusCode = 1, messageError = unknownError)
        }
    }
}
