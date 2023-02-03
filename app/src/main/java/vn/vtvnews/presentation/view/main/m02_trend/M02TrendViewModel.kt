package vn.vtvnews.presentation.view.main.m02_trend

import vn.vtvnews.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class M02TrendViewModel @Inject constructor(private val trendRepo: TrendRepository) : BaseViewModel() {

}