# Compose Utils Core

`Compose Utils Core` is a Compose Multiplatform utility for accessing platform data, handling
dispatchers, opening URLs, and managing shared preferences across multiple platforms.

## Features

- **Platform Information**: Retrieve device name, OS version, model, and manufacturer.
- **Dispatcher Provider**: Provides a common way to access coroutine dispatchers.
- **URL Handling**: Open URLs in the default browser.
- **Shared Preferences**: Store and retrieve simple key-value pairs across platforms.

[![Maven Central](https://img.shields.io/maven-central/v/io.github.the-best-is-best/compose-utils-core)](https://central.sonatype.com/artifact/io.github.the-best-is-best/compose-utils-core)

Compose Utils is available on `mavenCentral()`.

## Installation

Add the dependency to your Kotlin Multiplatform project:

```kotlin
dependencies {
    implementation("io.github.the-best-is-best:compose-utils-core:<latest-version>")
}
```

## Usage

### Platform Information

```kotlin
val platformData = PlatformData()
println("Device: ${platformData.deviceName}, OS: ${platformData.version}")
```

### Handling Dispatchers

```kotlin
val dispatcher = providerDispatcher().io
```

### Opening URLs

```kotlin
openUrl("https://example.com")
```

### Shared Preferences

```kotlin
val prefs = SharedPrefs()
prefs.put("theme", "dark")
val theme = prefs.get("theme")
println("Theme: $theme")
```

## Roadmap

More utilities and enhancements will be added soon!

## License

This project is licensed under the MIT License.
