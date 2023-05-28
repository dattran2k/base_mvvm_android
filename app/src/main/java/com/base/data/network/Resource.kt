package com.base.data.network

import com.base.util.InternetUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response


/**
 * This is used for getting states of network call
 */
sealed interface Resource<in T : Any> {
    class Success<T : Any>(val data: T) : Resource<T>
    object Loading : Resource<Any>
    class Error<T : Any>(val message: String, val code: Int, val data: T? = null) : Resource<T>


}

fun <T : Any> (suspend () -> Response<T>).toFlowSafeApi(
    dispatcher: CoroutineDispatcher,
): Flow<Resource<T>> {
    return flow {
        emit(Resource.Loading)
        if (!InternetUtil.isNetworkAvailable()) {
            emit(Resource.Error("No Internet", 0))
            return@flow
        }
        val response = this@toFlowSafeApi.invoke()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(Resource.Success(body))
                return@flow
            }
        }
        emit(Resource.Error(response.errorBody()?.string() ?: response.message(), response.code()))
    }.catch {
        emit(Resource.Error(it.message ?: "Lỗi", 0))
    }.flowOn(dispatcher)
}
