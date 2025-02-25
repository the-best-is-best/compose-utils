# Compose Utils Ktor Error Handler

`Compose Utils Ktor Error Handler` is a **Compose Multiplatform (CMP)** utility for handling Ktor
errors efficiently, providing structured error management across multiple platforms.

## Features

- **Structured Error Handling**: Easily map HTTP status codes to meaningful error messages.
- **Composable Initialization**: Allows customization of error messages using string resources.
- **Multi-Platform Support**: Works seamlessly with Ktor across different platforms.

## Download

[![Maven Central](https://img.shields.io/maven-central/v/io.github.the-best-is-best/compose-utils-ktor-error-handler)](https://central.sonatype.com/artifact/io.github.the-best-is-best/compose-utils-ktor-error-handler)

Compose Utils Ktor error handler is available on `mavenCentral()`.

## Installation

Add the dependency to your Kotlin Multiplatform project:

```kotlin
dependencies {
    implementation("io.github.the-best-is-best:compose-utils-ktor-error-handler:<latest-version>")
}
```

## Usage

### Initialize Custom Error Messages (Optional)

```kotlin
@Composable
fun App() {
    KtorErrorHandler.init(
        jsonConvertError = stringResource(Res.string.jsonConvertError),
        noInternetConnection = stringResource(
            Res.strings.noInternetConnection)
        ...
    )
}
```

### Handling Ktor Exceptions

```kotlin
suspend fun fetchData() {
    try {
        // Ktor API call
    } catch (e: Exception) {
        val failure = KtorErrorHandler.handle(e)
        println("Error: ${failure.messageError}")
    }
}
```

## Failure Model

```kotlin
data class Failure(
    val statusCode: Int? = null,
    val messageError: String? = null,
    val data: Any? = null
)
```

## License

This project is licensed under the MIT License.
