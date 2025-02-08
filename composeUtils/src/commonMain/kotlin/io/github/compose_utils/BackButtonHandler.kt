package io.github.compose_utils

import androidx.compose.runtime.Composable

@Composable
expect fun BackButtonHandler(enabled: Boolean = true, onBackPressed: () -> Unit)