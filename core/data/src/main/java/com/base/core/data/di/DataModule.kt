package com.base.core.data.di

import com.base.core.data.repository.todo.TodoRepository
import com.base.core.data.repository.todo.TodoRepositoryIml
import com.base.core.data.repository.user.UserDataRepository
import com.base.core.data.repository.user.UserDataRepositoryImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Here are the dependencies which will be injected by hilt
 *
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsUserRepository(
        userDataRepositoryImpl: UserDataRepositoryImpl,
    ): UserDataRepository

    @Binds
    fun bindTodoRepository(
        todoRepositoryImpl: TodoRepositoryIml,
    ): TodoRepository


}