package com.base.model.entity

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlin.reflect.KClass

data class HomeTabModel<T : Any>(
    val fragment: KClass<T>,
    @DrawableRes val icon: Int,
    @StringRes val title: Int
)
