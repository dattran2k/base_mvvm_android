package com.base.data.respository.user

import com.base.data.model.local.DarkThemeConfig
import com.base.data.model.local.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    /**
     * Stream of [UserData]
     */
    val userData: Flow<UserData>

    suspend fun updateDarkMode(darkThemeConfig : DarkThemeConfig)

}