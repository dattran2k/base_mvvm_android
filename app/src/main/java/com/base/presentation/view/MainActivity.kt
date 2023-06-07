package com.base.presentation.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.base.R
import com.base.core.util.InternetUtil
import com.base.databinding.M00ActivityMainBinding

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var binding: M00ActivityMainBinding
    var isBack = false
    var backHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = M00ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
        initListenInternet()
    }

    private fun getController() = findNavController(R.id.nav_host_fragment_content_main)
    private fun initNavigation() {
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (getController().previousBackStackEntry == null) {
                    if (!isBack) {
                        Toast.makeText(
                            this@MainActivity,
                            "Click 1 more time to back",
                            Toast.LENGTH_LONG
                        ).show()
                        isBack = true
                        backHandler.postDelayed({
                            isBack = false
                        }, 3000)
                    } else {
                        finish()
                    }
                } else {
                    getController().popBackStack()
                }
            }
        })
        getController().addOnDestinationChangedListener(this)
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

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        when (destination.id) {
            R.id.m00_main_fragment -> {

            }
        }
    }

    override fun onDestroy() {
        getController().removeOnDestinationChangedListener(this)
        super.onDestroy()
    }

}