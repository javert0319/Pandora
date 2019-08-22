package com.lib.fram.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lib.fram.groble.FramGroble
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

    fun detachView() {
        mViewRef?.clear()
        mViewRef = null
    }

    override fun onCleared() {
        super.onCleared()
        detachView()
    }
}
