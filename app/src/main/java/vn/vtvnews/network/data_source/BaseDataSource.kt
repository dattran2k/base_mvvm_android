package vn.vtvnews.network.data_source

import android.content.Context
import vn.vtvnews.common.Constants
import vn.vtvnews.helper.DialogSnackBarUtils
import vn.vtvnews.helper.InternetUtil
import vn.vtvnews.helper.PreferenceHelper
import vn.vtvnews.helper.Utility
import vn.vtvnews.network.Resource
import retrofit2.Response

abstract class BaseDataSource(private val mContext: Context) {

    /**
     * Try catch trong này
     * map data sang Resource
     */
    protected var deviceId: String = Utility.getDeviceId(mContext)
    protected var userId: String = PreferenceHelper.getInstance().get(Constants.Preference.PREFUSER_ID,"-1")

    suspend fun <T : Any> safeApiCall(
        apiCall: suspend () -> Response<T>,
    ): Resource<T> {
        try {
            PreferenceHelper.getInstance().get(Constants.Preference.PREF_TOKEN, "")
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