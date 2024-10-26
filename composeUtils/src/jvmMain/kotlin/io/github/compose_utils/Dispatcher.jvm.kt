package io.github.compose_utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class JvmDispatcher : Dispatcher {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

}

actual fun providerDispatcher(): Dispatcher = JvmDispatcher()