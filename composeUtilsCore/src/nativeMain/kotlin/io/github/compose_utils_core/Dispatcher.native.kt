package io.github.compose_utils_core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


internal class NativeDispatcher : Dispatcher {
    override val io: CoroutineDispatcher
        get() = Dispatchers.Default

}

actual fun providerDispatcher(): Dispatcher = NativeDispatcher()