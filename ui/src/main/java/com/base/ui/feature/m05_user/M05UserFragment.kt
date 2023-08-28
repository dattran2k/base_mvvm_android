package com.base.ui.feature.m05_user

import android.view.View
import androidx.fragment.app.viewModels
import com.base.data.model.local.DarkThemeConfig
import com.base.ui.databinding.M05FragmentUserBinding
import com.base.ui.feature.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class M05UserFragment : BaseFragment<M05FragmentUserBinding>(M05FragmentUserBinding::inflate) {

    private val viewModel: M05UserViewModel by viewModels()
    override fun initView() {
        binding.btnYes.setOnClickListener {
            viewModel.updateDarkMode(getStateSelected(it))
        }
        binding.btnNo.setOnClickListener {
            viewModel.updateDarkMode(getStateSelected(it))
        }
        binding.btnSystem.setOnClickListener {
            viewModel.updateDarkMode(getStateSelected(it))
        }
    }

    override fun CoroutineScope.initObserverCreated() {
        launch {
            viewModel.darkModeState.collect {
                updateSelectedRadio(it)
            }
        }
    }

    private fun updateSelectedRadio(darkThemeConfig: DarkThemeConfig) {
        val id = getRadioByConfig(darkThemeConfig)
        if (id != -1)
            binding.radioGroup.check(id)
    }

    private fun getStateSelected(view: View): DarkThemeConfig {
        return when (view) {
            binding.btnYes -> DarkThemeConfig.DARK
            binding.btnSystem -> DarkThemeConfig.FOLLOW_SYSTEM
            else -> DarkThemeConfig.LIGHT
        }
    }

    private fun getRadioByConfig(darkThemeConfig: DarkThemeConfig): Int {
        return when (darkThemeConfig) {
            DarkThemeConfig.DARK -> binding.btnYes.id
            DarkThemeConfig.FOLLOW_SYSTEM -> binding.btnSystem.id
            DarkThemeConfig.LIGHT -> binding.btnNo.id
        }
    }
}