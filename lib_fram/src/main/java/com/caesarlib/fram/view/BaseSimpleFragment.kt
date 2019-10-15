package com.caesarlib.fram.view

import com.caesarlib.fram.viewmodel.BaseSimpleViewModel
import com.caesarlib.fram.viewmodel.BaseViewModel

abstract class BaseSimpleFragment : BaseFragment<BaseView, BaseViewModel<BaseView>>() {
    override fun createViewModel(): BaseViewModel<BaseView> {
        return BaseSimpleViewModel()
    }
}
