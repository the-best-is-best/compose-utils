package compose_utils_navigation

import androidx.compose.runtime.Composable

@Composable
actual fun BackButtonHandler(enabled: Boolean, onBackPressed: () -> Unit) {
//    DisposableEffect(enabled) {
//        if (enabled) {
//            val listener = { _: Event -> onBackPressed() }
//
//            window.addEventListener("popstate", listener)
//
//            onDispose {
//                window.removeEventListener("popstate", listener)
//            }
//        }
//
//        onDispose { } // Required by DisposableEffect
    // }


}