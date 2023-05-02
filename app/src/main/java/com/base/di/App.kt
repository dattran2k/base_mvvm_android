package com.base.di

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.base.BuildConfig
import com.base.common.Constants
import com.base.util.InternetUtil
import com.base.util.LogsUtil
import com.base.util.PreferenceHelper
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    private object AppHolder {
        val appHolder = App()
    }
    companion object {
        fun getInstance() = AppHolder.appHolder
    }
    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.getInstance().init(this)
        FirebaseApp.initializeApp(this)
        InternetUtil.init(this)

        if (BuildConfig.FLAVOR == Constants.FLAVOR_DEVELOP) {
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false)
            Timber.plant(LogsUtil())
        }
    }
}