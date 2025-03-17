package io.github.compose_utils_core

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatcher {
    /** io in android and default in any another platform */
    val io: CoroutineDispatcher
}

expect fun providerDispatcher(): Dispatcher


