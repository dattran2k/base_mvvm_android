package com.base.presentation.view.main.m05_user

import android.view.View
import androidx.fragment.app.viewModels
import com.base.core.common.DataStorePref
import com.base.databinding.M05FragmentUserBinding
import com.base.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

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

    override suspend fun initObserverCreated() {
        viewModel.darkModeState.collect {
            updateSelectedRadio(it)
        }
    }

    private fun updateSelectedRadio(value: Int?) {
        val id = getRadioByState(value)
        if (id != -1)
            binding.radioGroup.check(id)
    }

    private fun getStateSelected(view: View): Int {
        return when (view) {
            binding.btnYes -> DataStorePref.DARK_MODE_ENABLE
            binding.btnSystem -> DataStorePref.DARK_MODE_SYSTEM
            else -> DataStorePref.DARK_MODE_UN_ENABLE
        }
    }

    private fun getRadioByState(state: Int?): Int {
        return when (state) {
            DataStorePref.DARK_MODE_ENABLE -> binding.btnYes.id
            DataStorePref.DARK_MODE_SYSTEM -> binding.btnSystem.id
            DataStorePref.DARK_MODE_UN_ENABLE -> binding.btnNo.id
            else -> -1
        }
    }
}