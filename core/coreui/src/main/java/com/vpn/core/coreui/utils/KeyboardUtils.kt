package com.vpn.core.coreui.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


object KeyboardUtils {
    fun hideSoftKeyboard(activity: Activity?) {
        if (activity == null) return
        activity.currentFocus?.let { focusedView ->
            (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
                hideSoftInputFromWindow(focusedView.windowToken, 0)
            }
        }
    }

    fun showSoftKeyboard(activity: Activity?) {
        if (activity == null) return
        (activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
            toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }
    }

    fun hideSoftKeyboard(view: View) {
        view.context?.let {
            (view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
                hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }
}