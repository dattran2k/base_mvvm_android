package com.news.thanhnien.base

import com.news.thanhnien.network.StateStatus

/**
 * Đây là để copy tạo state mới , không phải là để extends
 * Status base chỉ có 3 phần cơ bản Có thể tạo Status mới nếu cần thiết
 *
 * */
data class BaseState(
    val data: Any? = null,
    val stateStatus: StateStatus? = null,
    val msg: String? = null
) {
    companion object {
        fun loadSuccess(data: Any, msg: String? = null) = BaseState(data, StateStatus.SUCCESS, msg)

        fun loading(msg: String? = null) =
            BaseState(null, StateStatus.LOADING, msg)

        fun loadFail(data: Any? = null, msg: String? = null) = BaseState(data, StateStatus.FAIL, msg)
    }
}