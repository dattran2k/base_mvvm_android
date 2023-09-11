package com.base.core.model.network

sealed interface Resource<in T : Any> {
    class Success<T : Any>(val data: T) : Resource<T>
    data object Loading : Resource<Any>
    class Error<T : Any>(val message: String, val code: Int, val data: T? = null) : Resource<T>
}