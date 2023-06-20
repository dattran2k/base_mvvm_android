package com.base.core.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatDelegate
import com.base.data.model.local.DarkThemeConfig

object Utility {
    //hide keyboard
    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun setAppMode(darkThemeConfig: DarkThemeConfig) {
        AppCompatDelegate.setDefaultNightMode(
            when (darkThemeConfig) {
                DarkThemeConfig.FOLLOW_SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                DarkThemeConfig.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                DarkThemeConfig.DARK -> AppCompatDelegate.MODE_NIGHT_YES
            }
        )
    }
}