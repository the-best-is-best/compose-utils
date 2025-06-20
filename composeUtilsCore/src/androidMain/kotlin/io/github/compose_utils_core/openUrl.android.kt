package io.github.compose_utils_core

import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri

actual fun openUrl(url: String) {
    val uri = url.toUri()
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    applicationContext.startActivity(intent)
}

fun openUrl(uri: Uri) {
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    applicationContext.startActivity(intent)
}