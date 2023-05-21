package com.base.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Here are the dependencies which will be injected by hilt
 *
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    //Gson for converting JSON String to Java Objects
    @Provides
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun providerContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideApp(): MyApplication {
        return MyApplication()
    }
    //Api Service with retrofit instance

    @Provides
    @Singleton
    fun provideCoroutinesDispatcher() = Dispatchers.IO
}