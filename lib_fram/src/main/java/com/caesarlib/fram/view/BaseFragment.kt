package com.caesarlib.fram.view

import android.os.Bundle
import com.caesarlib.fram.viewmodel.BaseViewModel

abstract class BaseFragment<V, T : BaseViewModel<V>> : ToolBarFragment(), BaseView {

    protected var mViewModel: T? = null //ViewModel对象

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = createViewModel() //创建ViewModel
        mViewModel?.attachView(this as V) //View与ViewModel建立关系
    }

    //创建ViewModel
    protected abstract fun createViewModel(): T

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }

}
