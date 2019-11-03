package com.caesarlib.fram.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.caesarlib.fram.groble.FramGroble
import com.caesarlib.network.ApiService
import com.caesarlib.network.NetFacede
import com.caesarlib.network.bean.base.BaseYesApiBean
import com.caesarlib.network.bean.yesapi.UserLoginData
import com.caesarlib.res_tools.CSLog
import kotlinx.coroutines.cancel
import java.lang.ref.Reference
import java.lang.ref.WeakReference

/**
 * viewmodel的基类
 * created by Caesar on 2019/1/25
 * email : 15757855271@163.com
 */
abstract class BaseViewModel<V> : AndroidViewModel(FramGroble.getApp() as Application) {

    protected var mViewRef: Reference<V>? = null

    fun attachView(view: V?) {
        mViewRef = WeakReference<V>(view)
    }

    suspend fun <T> saveApiCall(call: suspend () -> BaseYesApiBean<T>): BaseYesApiBean<T> {
        return try {
            call.invoke()
        } catch (e: Exception) {
            BaseYesApiBean()
        }
    }

    fun getDefaultApiService(): ApiService {
        return NetFacede.getInstance().defaultService
    }

    fun detachView() {
        CSLog.d("调用了detachView")
        mViewRef?.clear()
        mViewRef = null
        viewModelScope.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        detachView()
    }
}
