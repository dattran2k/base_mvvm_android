package com.news.thanhnien.network

interface ApiCallBack<T> {
    fun onSuccess(data: T)
    fun onFail(data: T? = null, message: String? = null)
}