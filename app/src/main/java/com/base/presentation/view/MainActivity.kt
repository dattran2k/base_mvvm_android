package com.base.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.base.R
import com.base.core.util.InternetUtil
import com.base.databinding.ActivityMainBinding
import com.base.presentation.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        NavigationManager.getInstance().init(this, R.id.nav_host_fragment_content_main, this)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                InternetUtil.internetState.collect {
                    Timber.i("Internet enable = $it")
                }
            }
        }
    }

    override fun onBackStackChanged() {
        val currentFragment = NavigationManager.getInstance().getCurrentFragment() ?: return
        Timber.e("onBackStackChanged: $currentFragment ")
    }

    override fun onDestroy() {
        NavigationManager.getInstance().release()
        super.onDestroy()

    }
}