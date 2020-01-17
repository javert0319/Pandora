package com.caesarlib.fram.viewmodel

import com.caesarlib.network.ApiService
import com.caesarlib.network.NetFacede
import com.caesarlib.network.bean.base.BaseYesApiBean
import com.caesarlib.res_tools.CSLog

abstract class NormalNetModel {
    suspend fun <T> saveApiCall(call: suspend () -> BaseYesApiBean<T>?): BaseYesApiBean<T>? {
        return try {
            call.invoke()
        } catch (e: Exception) {
            CSLog.d("出现了异步异常")
            BaseYesApiBean()
        }
    }

    fun getDefaultApiService(): ApiService? {
        return NetFacede.instance.defaultService
    }
}