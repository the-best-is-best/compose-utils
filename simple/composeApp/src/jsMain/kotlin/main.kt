import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import io.github.sample.NetworkConnectivityScreen
import kotlinx.browser.document
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        val body = document.body ?: return@onWasmReady
        ComposeViewport(body) {
            NetworkConnectivityScreen()
        }
    }
}
