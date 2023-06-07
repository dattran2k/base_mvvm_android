package com.base.presentation.view.main.m05_user

import androidx.lifecycle.viewModelScope
import com.base.core.common.DataStorePref
import com.base.core.datastore.DataStoreManager
import com.base.core.util.Utility
import com.base.data.respository.DemoRepository
import com.base.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class M05UserViewModel @Inject constructor(
    private val demoRepository: DemoRepository,
    private val dataStoreManager: DataStoreManager,
) : BaseViewModel() {
    val darkModeState = dataStoreManager.getValue(DataStorePref.PREF_DARK_MODE).distinctUntilChanged().stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DataStorePref.DARK_MODE_UN_ENABLE
    )

    fun updateDarkMode(state: Int) {
        if (darkModeState.value != state) {
            viewModelScope.launch {
                dataStoreManager.storeValue(DataStorePref.PREF_DARK_MODE, state)
            }
        }
    }
}