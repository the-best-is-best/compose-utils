<h1 align="center">Compose Utils</h1><br>

<div align="center">
<a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
<a href="https://android-arsenal.com/api?level=21" rel="nofollow">
    <img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat" style="max-width: 100%;">
</a>
<img src="https://img.shields.io/badge/Platform-Android-brightgreen.svg?logo=android" alt="Badge Android" />
  <img src="https://img.shields.io/badge/Platform-iOS%20%2F%20macOS-lightgrey.svg?logo=apple" alt="Badge iOS" />
  <img src="https://img.shields.io/badge/Platform-JVM-8A2BE2.svg?logo=openjdk" alt="Badge JVM" />
    <img src="https://img.shields.io/badge/Platform-WASM%20%2F%20JS-yellow.svg?logo=javascript" alt="Badge JS" />
<a href="https://github.com/the-best-is-best/"><img alt="Profile" src="https://img.shields.io/badge/github-%23181717.svg?&style=for-the-badge&logo=github&logoColor=white" height="20"/></a>

</div>

ComposeUtils is a utility library designed to streamline development in Compose Multiplatform
projects. This package provides a set of platform-specific utilities and shared abstractions
tailored for use in Compose environments across Android, iOS, and other platforms supported by
Kotlin Multiplatform. With tools for managing coroutine dispatchers, accessing platform information,
handling preferences, and launching URLs, ComposeUtils simplifies cross-platform development by
offering consistent, easy-to-use interfaces and functions that work seamlessly in Compose-based
projects.

## 📦 Utility Libraries

- **Compose Utils Core**  
  ➜ [`Compose Utils Core`](composeUtilsCore/README.md)

- **Compose Utils Navigation**  
  ➜ [`Compose Utils Navigation`](composeUtilsNavigation/README.md)

---

## 🌐 Networking

- **Network Connectivity**  
  ➜ [`Network Connectivity`](networkConnectivity/README.md)

- **Ktor Error Handling**  
  ⚠️ **Deprecated**  
  ➜ [`Compose Ktor Error Handler`](ktorErrorHandler/README.md)
  > This library is no longer maintained or updated.  
  > You are free to copy and reuse its source code as needed.  
  > Feel free to adapt the logic to match your API error response structure and validation format.

## open url

```kotlin
openUrl("https://maps.app.goo.gl/qnPYcxLP9Y9Y74od8")
```

## Prefs

### functionality

```kotlin
expect class SharedPrefs() {
    fun putInt(key: String, value: Int)
    fun putString(key: String, value: String)
    fun putBool(key: String, value: Boolean)
    fun getInt(key: String, default: Int?): Int
    fun getString(key: String): String?
    fun getBool(key: String, default: Boolean?): Boolean
    fun remove(key: String)

}
```

### How use it

```kotlin
    val prefs = SharedPrefs()
...
ElevatedButton(onClick = {
    prefs.putInt() // or any function need
})
```

## Platform details

### Functionality

```kotlin
expect class PlatformData() {
    val name: String
    val deviceName: String
    val version: String
    val model: String
    val manufacturer: String
}
```

### How to use it

```kotlin
    val platformData = PlatformData()
...
ElevatedButton(onClick = {
    println(platformData.name)
})
```

- Note support Either<out L, out R>

## V 1.1.0

- Support Ktor error handler

### First need run this one time

```kotlin
KtorErrorHandler().init()
```

### Second after catch error

```kotlin

internal class GetLecturesFilterRepository(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun invoke(lecturesFilterRequest: LecturesFilterRequest): Either<Failure, LecturesFilterModel> {
        return try {
            val result = remoteDataSource.getLecturesFilter(lecturesFilterRequest)
            val data = result.toModel()
            Either.Right(data)
        } catch (e: Exception) {
            Either.Left(KtorErrorHandler().handle(e))
        }
    }
}
```

### Third need get error from data example

```kotlin
    val result = loginUseCase.invoke(loginRequest)

     result.fold(
        right = {
            data = it
        },
        left = {
            state.value = state.value.copy(errorMessage = (it as Map)["errorMessage"],
            errorForm = (it as Map)["invalid fields"])
        }
    )

```
