package vn.vtvnews.base

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
import vn.vtvnews.helper.DialogSnackBarUtils
import dagger.hilt.android.AndroidEntryPoint
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
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(object :
                ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {

                }

                override fun onLost(network: Network) {
                    DialogSnackBarUtils.showDisconnectSnackBar()
                }
            })
        }
        onBackPressedDispatcher.addCallback(this , object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Timber.e("onBackPressed ${supportFragmentManager.backStackEntryCount}")
                if (supportFragmentManager.backStackEntryCount== 0) {
                    if (!isBack) {
                        Toast.makeText(this@BaseActivity, "Click 1 lần nữa để thoát app", Toast.LENGTH_LONG).show()
                        isBack = true
                        backHandler.postDelayed({
                            isBack = false
                        }, TIME)
                    } else {
                        finish()
                    }
                } else {
                    supportFragmentManager.popBackStack()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        backHandler.removeCallbacksAndMessages(null)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

}