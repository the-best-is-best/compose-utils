package io.github.composeutilsandroid

import android.app.Activity
import java.lang.ref.WeakReference

object AndroidComposeUtils {
    private var activity: WeakReference<Activity?> = WeakReference(null)

    fun getActivity(): Activity {
        return activity.get()!!
    }

    fun initialization(activity: Activity) {
        AndroidComposeUtils.activity = WeakReference(activity)
    }
}