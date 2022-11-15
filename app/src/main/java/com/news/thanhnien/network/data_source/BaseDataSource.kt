package com.news.thanhnien.network.data_source

import android.content.Context
import com.news.thanhnien.common.Constants
import com.news.thanhnien.helper.DialogSnackBarUtils
import com.news.thanhnien.helper.InternetUtil
import com.news.thanhnien.helper.PreferenceHelper
import com.news.thanhnien.helper.Utility
import com.news.thanhnien.network.Resource
import retrofit2.Response

abstract class BaseDataSource(private val mContext: Context) {

    /**
     * Try catch trong này
     * map data sang Resource
     */
    protected var deviceId: String = Utility.getDeviceId(mContext)
    protected var userId: String = PreferenceHelper.getInstance().get(Constants.Preference.USER_ID,"-1")

    suspend fun <T : Any> safeApiCall(
        apiCall: suspend () -> Response<T>,
    ): Resource<T> {
        try {
            PreferenceHelper.getInstance().get(Constants.Preference.TOKEN, "")
            if (!InternetUtil.isNetworkAvailable()) {
                DialogSnackBarUtils.showDisconnectSnackBar()
                return Resource.error("Không có kết nối mạng")
            }
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    return Resource.success(body)
                }
            }
//            Log.e("DEBUG", response.code().toString())
            val errorBody = response.errorBody()?.string() ?: "Lỗi"
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