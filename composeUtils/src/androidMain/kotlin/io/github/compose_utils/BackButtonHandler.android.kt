package io.github.compose_utils

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable


@Composable
actual fun BackButtonHandler(enabled: Boolean, onBackPressed: () -> Unit) {
    BackHandler(enabled,onBackPressed)
}