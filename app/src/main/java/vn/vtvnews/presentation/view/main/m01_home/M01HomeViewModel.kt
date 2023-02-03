package vn.vtvnews.presentation.view.main.m01_home

import androidx.lifecycle.viewModelScope
import vn.vtvnews.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class M01HomeViewModel @Inject constructor(private val homeRepo: HomeRepository) : BaseViewModel() {
    fun getData(){
        viewModelScope.launch {
            homeRepo.getHabitSites().collect{
                Timber.e(it.toString())
            }
        }
    }
}

