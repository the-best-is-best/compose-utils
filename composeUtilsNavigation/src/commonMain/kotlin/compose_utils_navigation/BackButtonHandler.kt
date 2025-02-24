package compose_utils_navigation

import androidx.compose.runtime.Composable

@Composable
expect fun BackButtonHandler(enabled: Boolean = true, onBackPressed: () -> Unit)