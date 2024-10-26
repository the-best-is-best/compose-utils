package io.github.compose_utils

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatcher {
    /** io in android and default in any another platform */
    val io: CoroutineDispatcher
}

expect fun providerDispatcher(): Dispatcher