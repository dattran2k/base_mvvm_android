package com.base.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

data class HomeTabModel<T : Fragment>(
    val fragment: KClass<T>,
    @DrawableRes val icon: Int,
    @StringRes val title: Int
)
