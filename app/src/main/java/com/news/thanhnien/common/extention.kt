package com.news.thanhnien.common

import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import com.news.thanhnien.network.ApiCallBack
import com.news.thanhnien.network.Resource

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.visible(isVisible: Boolean) {
    this?.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.invisible() {
    this?.visibility = View.INVISIBLE
}

fun <T> Resource<T>.setCallBack(callBack: ApiCallBack<T>) {
    if (status == Resource.Status.SUCCESS && data != null) {
        callBack.onSuccess(this.data)
    } else {
        callBack.onFail(this.data, message)
    }
}
