import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import io.github.sample.NetworkConnectivityScreen

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("sample") {
        NetworkConnectivityScreen()
    }
}
