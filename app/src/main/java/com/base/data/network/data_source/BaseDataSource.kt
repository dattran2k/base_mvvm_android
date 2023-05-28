package com.base.data.network.data_source

import com.base.util.InternetUtil
import com.base.data.network.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response


abstract class BaseDataSource(private val dispatcher: CoroutineDispatcher) {


}