package compose_utils_navigation

import androidx.compose.runtime.Composable
import kotlinx.cinterop.ExperimentalForeignApi

//class SwipeHandler(private val onBackPressed: () -> Unit) : NSObject() {
//    @ObjCAction
//    fun handleSwipe() {
//        println("Swipe worked!")
//        onBackPressed()
//    }
//}

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun BackButtonHandler(enabled: Boolean, onBackPressed: () -> Unit) {
//    val uiView: UIView? = UIApplication.sharedApplication.delegate?.window?.rootViewController?.view
//
//    if (uiView == null) {
//        println("No UIView found")
//        return
//    }
//
//    DisposableEffect(uiView, enabled) {
//        if (enabled) {
//            val handler = SwipeHandler(onBackPressed)
//            val swipe = UISwipeGestureRecognizer(handler, sel_registerName("handleSwipe")).apply {
//                direction = UISwipeGestureRecognizerDirectionRight
//            }
//            uiView.addGestureRecognizer(swipe)
//            println("Gesture added to $uiView")
//        }
//
//        onDispose {
//            if (enabled) {
//                val swipe = uiView.gestureRecognizers?.firstOrNull { it is UISwipeGestureRecognizer } as? UISwipeGestureRecognizer
//                swipe?.let { uiView.removeGestureRecognizer(it) }
//                println("Gesture removed")
//            }
//        }
//    }
}