package com.base.presentation.view.m00_main.m05_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.data.model.local.DarkThemeConfig
import com.base.data.respository.user.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class M05UserViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : ViewModel() {
    val darkModeState = userDataRepository.userData.map {
        it.darkThemeConfig
    }.distinctUntilChanged().stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DarkThemeConfig.LIGHT
    )

    fun updateDarkMode(darkThemeConfig: DarkThemeConfig) {
        viewModelScope.launch {
            userDataRepository.updateDarkMode(darkThemeConfig)
        }
    }
}