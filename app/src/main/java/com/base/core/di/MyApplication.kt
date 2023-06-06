package com.base.core.di

import android.app.Application
import com.base.BuildConfig
import com.base.core.common.Constants
import com.base.core.common.DataStorePref
import com.base.core.datastore.DataStoreManager
import com.base.core.util.InternetUtil
import com.base.core.util.LogsUtil
import com.base.core.util.Utility
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltAndroidApp
class MyApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    @Inject
    lateinit var dataStoreManager: DataStoreManager
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        InternetUtil.init(this)
        if (BuildConfig.FLAVOR == Constants.FLAVOR_DEVELOP) {
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false)
            Timber.plant(LogsUtil())
        }
        applicationScope.launch {
            dataStoreManager.getValue(DataStorePref.PREF_DARK_MODE).take(1).collect {
                Utility.setAppMode(it ?: DataStorePref.DARK_MODE_UN_ENABLE)
            }
        }

    }
}