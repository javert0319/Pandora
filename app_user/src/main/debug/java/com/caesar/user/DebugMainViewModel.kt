package com.caesar.user

import androidx.lifecycle.viewModelScope
import com.caesarlib.fram.view.BaseView
import com.caesarlib.fram.viewmodel.BaseViewModel
import com.caesarlib.network.NetFacede
import com.caesarlib.network.ParamsFactary
import com.caesarlib.network.YesApiServiceName
import com.caesarlib.res_tools.CSLog
import com.caesarlib.res_tools.CaesarStringDealTool
import kotlinx.coroutines.*

class DebugMainViewModel : BaseViewModel<BaseView>() {

    fun doNetTest() {
        viewModelScope.launch(Dispatchers.IO) {
            CSLog.d("主调用了")
            delay(5000)
            CSLog.d("主到了")
        }

    }
}