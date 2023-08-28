package com.base

import android.app.Application
import com.base.core.datastore.PreferenceDataSource
import com.base.core.common.util.InternetUtil
import com.base.core.common.util.LogsUtil
import com.base.core.common.util.Utility
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltAndroidApp
class MyApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    @Inject
    lateinit var userPreferences: PreferenceDataSource
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        InternetUtil.init(this)
        if (BuildConfig.DEBUG) {
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false)
            Timber.plant(LogsUtil())
        }
        // TODO check this better way
        applicationScope.launch {
            userPreferences.userData.map {
                it.darkThemeConfig
            }.distinctUntilChanged().collect {
                Utility.setAppMode(it)
            }
        }
    }
}