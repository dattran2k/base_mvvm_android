package com.base.data.network

import javax.inject.Inject


class ApiDataSource @Inject constructor(private val demoServiceRetrofit: DemoServiceRetrofit) {
    suspend fun getTodos( ) = demoServiceRetrofit.getTodos()
}

