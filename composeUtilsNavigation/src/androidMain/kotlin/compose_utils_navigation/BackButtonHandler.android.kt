package compose_utils_navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable


@Composable
actual fun BackButtonHandler(enabled: Boolean, onBackPressed: () -> Unit) {
    BackHandler(enabled, onBackPressed)
}