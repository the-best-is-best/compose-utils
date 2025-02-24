# Compose Utils Android

`compose-utils-android` provides a **Back Button Handler** utility for Jetpack Compose, making it
easy to manage back button behavior in Android applications.

> ⚠️ **Note:** This utility works **only on Android**.

---

## Download

[![Maven Central](https://img.shields.io/maven-central/v/io.github.the-best-is-best/compose-utils-android)](https://central.sonatype.com/artifact/io.github.the-best-is-best/compose-utils-android)

Compose Utils Android is available on `mavenCentral()`.

## **Installation**

Add the dependency in your **build.gradle.kts**:

```kotlin
dependencies {
    implementation("io.github.the-best-is-best:compose-utils-android:VERSION")
}
```

## This package for access Activity

### in AndroidMain

```kotlin
 AndroidComposeUtils.initialization(this)
```

### Package need it

>
> - Compose Utils Core
>- Compose Utils Network Activity
