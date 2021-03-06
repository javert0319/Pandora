package com.caesarlib.fram.view

import com.caesarlib.fram.viewmodel.BaseViewModel
import com.caesarlib.fram.viewmodel.BaseSimpleViewModel


/**
 * created by Caesar on 2019/1/25
 * email : 15757855271@163.com
 */
abstract class BaseSimpleActivity : BaseActivity<BaseView, BaseViewModel<BaseView>>(), BaseView {

    override fun createViewModel(): BaseViewModel<BaseView> {
        return BaseSimpleViewModel()
    }

}
