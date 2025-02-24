import androidx.compose.ui.window.ComposeUIViewController
import io.github.sample.NetworkConnectivityScreen
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { NetworkConnectivityScreen() }
