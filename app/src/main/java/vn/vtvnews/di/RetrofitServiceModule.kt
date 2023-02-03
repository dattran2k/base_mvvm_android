package vn.vtvnews.di

import android.content.Context
import android.content.SharedPreferences
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import vn.vtvnews.BuildConfig
import vn.vtvnews.common.Constants
import vn.vtvnews.common.Constants.Preference.PREFUSER_ID
import vn.vtvnews.common.HeaderRetrofitEnum
import vn.vtvnews.helper.Utility
import vn.vtvnews.network.service.VtvNewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitServiceModule {

    private fun getHttpClient(
        context: Context,
        preferenceHelper: SharedPreferences,
        headerRetrofitEnum: HeaderRetrofitEnum = HeaderRetrofitEnum.NONE
    ): OkHttpClient {
        val deviceId = Utility.getDeviceId(context)
        val userId = preferenceHelper.getString(PREFUSER_ID, "-1") ?: "-1"
        return OkHttpClient.Builder().also { client ->
            client.retryOnConnectionFailure(true)
            client.addInterceptor {
                val newRequest = it.request().newBuilder().apply {
                    addHeader("user-id", userId)
                    if (headerRetrofitEnum == HeaderRetrofitEnum.NOTIFICATION)
                        addHeader("session-id", deviceId)
                    else
                        addHeader("device-id", deviceId)
                }.build()
                it.proceed(newRequest)
            }
            if (BuildConfig.DEBUG) {
                val loggingContent = HttpLoggingInterceptor()
                loggingContent.setLevel(HttpLoggingInterceptor.Level.BODY)
                val collector = ChuckerCollector(context)
                val logging = ChuckerInterceptor.Builder(context).alwaysReadResponseBody(true)
                    .collector(collector).build()
                client.interceptors().add(logging)
                client.interceptors().add(loggingContent)
            }
            client.connectTimeout(120, TimeUnit.SECONDS)
            client.readTimeout(120, TimeUnit.SECONDS)
            client.protocols(Collections.singletonList(Protocol.HTTP_1_1))
        }.build()
    }

    @Provides
    @Singleton
    @Named(Constants.Inject.API)
    fun providePegaRetrofit(
        gson: Gson,
        context: Context,
        preferences: SharedPreferences
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(getHttpClient(context, preferences))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
    @Provides
    @Singleton
    fun providePegaService(@Named(Constants.Inject.API) retrofit: Retrofit): VtvNewsService =
        retrofit.create(VtvNewsService::class.java)

}


