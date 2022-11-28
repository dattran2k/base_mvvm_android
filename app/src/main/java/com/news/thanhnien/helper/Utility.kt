package com.news.thanhnien.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.news.thanhnien.R

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
    fun setImage(context: Context, imageView: ImageView, imageUrl: String?, radius: Int = 1) {
        if (imageUrl.isNullOrBlank()) {
            imageView.setImageResource(R.mipmap.ic_launcher_round)
            return
        }
        val request = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(true)
            .centerCrop()
            .transforms(
                CenterCrop(), RoundedCorners(radius)
            )
        Glide.with(context).load(imageUrl).apply(request)
            .error(R.mipmap.ic_launcher_round).into(imageView)
    }

    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context?): String {
        return Settings.Secure.getString(context?.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun gcm(a: Long, b: Long): Long {
        return if (b == 0L) a else gcm(b, a % b)
    }

}