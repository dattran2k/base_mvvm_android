package com.base.presentation.base

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.base.util.InternetUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    companion object {
        const val TIME = 5000L
    }
    var isBack = false
    var backHandler = Handler(Looper.getMainLooper())

    @Inject
    protected lateinit var sharePreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListenInternet()
    }

    private fun initListenInternet() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                InternetUtil.internetState.collect {
                    Timber.i("Internet enable = $it")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        backHandler.removeCallbacksAndMessages(null)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

}