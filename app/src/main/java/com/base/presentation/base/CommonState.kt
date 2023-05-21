package com.base.presentation.base


sealed class CommonState<in T : Any> private constructor() {
    object Init : CommonState<Any>()
    class Success<T : Any>(val data: T) : CommonState<T>()
    class Loading : CommonState<Any>()
    class Error(val msg: String?) : CommonState<Any>()

}