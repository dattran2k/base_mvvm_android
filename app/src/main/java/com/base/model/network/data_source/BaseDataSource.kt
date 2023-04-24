package com.base.model.network.data_source

import android.content.Context
import com.base.helper.InternetUtil
import com.base.model.network.Resource
import retrofit2.Response

abstract class BaseDataSource(private val mContext: Context) {


    suspend fun <T : Any> safeApiCall(
        apiCall: suspend () -> Response<T>,
    ): Resource<T> {
        try {
            if (!InternetUtil.isNetworkAvailable()) {

                return Resource.error("No Internet")
            }
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Resource.success(body)
                }
            }
//            Log.e("DEBUG", response.code().toString())
            val errorBody = response.errorBody()?.string() ?: "Error"
            if (response.code() == 400) {
//                Timber.d(errorBody.toJson())
                try {
                    return Resource.error(errorBody)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            return Resource.error(
                "${response.code()}|${response.message()}",
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.error(e.message ?: "Lỗi")
        }
    }
}