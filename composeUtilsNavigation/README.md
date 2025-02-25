# Compose Utils Navigation

`Compose Utils Navigation` is a lightweight and efficient navigation utility for **Compose
Multiplatform (CMP)**, simplifying navigation management across multiple platforms.

## Features

- **Back Button Handling (Android Only)**: Provides a composable function to handle back button
  actions on Android.
- **Composable First**: Designed specifically for Compose Multiplatform.
- **Multi-Platform Package**: While the package is available for all platforms, the back button
  handling currently works on Android only.

## Download

[![Maven Central](https://img.shields.io/maven-central/v/io.github.the-best-is-best/compose-utils-navigation)](https://central.sonatype.com/artifact/io.github.the-best-is-best/compose-utils-navigation)

Compose Utils Navigation is available on `mavenCentral()`.

## Installation

Add the dependency to your Kotlin Multiplatform project:

```kotlin
dependencies {
    implementation("io.github.the-best-is-best:compose-utils-navigation:<latest-version>")
}
```

## Usage

### Handling Back Button (Android Only)

```kotlin
@Composable
fun SampleScreen() {
    BackButtonHandler(enabled = true) {
        println("Back button pressed!")
    }
}
```

## Roadmap

More navigation utilities and enhancements will be added soon!

## License

This project is licensed under the MIT License.
