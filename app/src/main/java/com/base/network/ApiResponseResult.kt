package com.base.network

data class ApiResponseResult<T>(
    var code: Int,
    var message: String,
    var status: Int,
    var result: T,
){
}