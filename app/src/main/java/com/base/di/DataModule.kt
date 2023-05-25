package com.base.di

import android.content.Context
import android.content.SharedPreferences
import com.base.data.network.data_source.ApiDataSource
import com.base.data.respository.DemoRepository
import com.base.data.respository.DemoRepositoryIml
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

/**
 * Here are the dependencies which will be injected by hilt
 *
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun providesSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("news.thanhnien", Context.MODE_PRIVATE)
    }

    @Provides
    fun bindsDemoRepository(
        dataSource: ApiDataSource,
        @Dispatcher(NiaDispatchers.IO) dispatcher: CoroutineDispatcher,
    ): DemoRepository {
        return DemoRepositoryIml(dataSource, dispatcher)
    }
}