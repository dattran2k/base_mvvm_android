package com.base.core.data.repository.user


import com.base.core.datastore.PreferenceDataSource
import com.base.data.model.local.DarkThemeConfig
import com.base.data.model.local.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(private val preferenceDataSource: PreferenceDataSource) :
    UserDataRepository {

    override val userData: Flow<UserData> = preferenceDataSource.userData

    override suspend fun updateDarkMode(darkThemeConfig: DarkThemeConfig) {
        preferenceDataSource.updateDarkMode(darkThemeConfig)

    }
}