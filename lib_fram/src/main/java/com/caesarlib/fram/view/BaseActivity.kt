package com.lib.fram.view

import android.os.Bundle
import com.lib.fram.viewmodel.BaseViewModel

/**基类activity
 * created by Caesar on 2019/1/25
 * email : 15757855271@163.com
 */
abstract class BaseActivity<V, T : BaseViewModel<V>> : ToolBarActivity(), BaseView {
    protected var mViewModel: T? = null
    private var isFirstResume = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = createViewModel()
        mViewModel?.attachView(this as? V) //View与ViewModel建立关系
    }

    protected abstract fun createViewModel(): T
    protected abstract fun onFirstResume()
    override fun onResume() {
        super.onResume()
        if (isFirstResume) {
            isFirstResume = false
            onFirstResume()
        }
    }

    override fun onDestroy() {
        mViewModel?.detachView()
        super.onDestroy()
    }
}
