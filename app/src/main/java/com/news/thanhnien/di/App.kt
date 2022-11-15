package com.news.thanhnien.di

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.news.thanhnien.BuildConfig
import com.news.thanhnien.common.Constants
import com.news.thanhnien.helper.InternetUtil
import com.news.thanhnien.helper.LogsUtil
import com.news.thanhnien.helper.PreferenceHelper
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
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