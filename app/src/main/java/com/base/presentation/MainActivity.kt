package com.base.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import com.base.R
import com.base.presentation.base.BaseActivity
import com.base.databinding.M00ActivityMainBinding

import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    lateinit var binding: M00ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = M00ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
    }

    private fun getController() = findNavController(R.id.nav_host_fragment_content_main)
    private fun initNavigation() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Timber.e("onBackPressed ${supportFragmentManager.backStackEntryCount}")
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
                        }, TIME)
                    } else {
                        finish()
                    }
                } else {
                    getController().popBackStack()
                }
            }
        })

        getController().addOnDestinationChangedListener { controller, destination, arguments ->
            Timber.e("Destination Change : ${destination.label}")
            when (destination.id) {
                R.id.m00_main_fragment -> {

                }
            }
        }

    }

}