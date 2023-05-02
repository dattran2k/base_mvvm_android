package com.base.presentation.base


sealed class CommonState<out T : Any> private constructor() {
    class Init<T : Any>() : CommonState<T>()
    class Success<T : Any>(val data: T) : CommonState<T>()
    class Loading<T : Any> : CommonState<T>()
    class Error<T : Any>(val msg: String?) : CommonState<T>()

}