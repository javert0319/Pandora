package com.caesar.user

import androidx.lifecycle.viewModelScope
import com.caesarlib.fram.view.BaseView
import com.caesarlib.fram.viewmodel.BaseViewModel
import com.caesarlib.network.ParamsFactary
import com.caesarlib.network.YesApiServiceName
import com.caesarlib.res_tools.CaesarStringDealTool
import com.caesarlib.userinfo.ValueUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DebugMainViewModel : BaseViewModel<BaseView>() {

    fun doNetTest() {


        viewModelScope.launch(Dispatchers.IO) {
            val result = saveApiCall {
                getDefaultApiService()?.userLogin(
                    ParamsFactary.userLoginARegisterParam(
                        YesApiServiceName.LOGIN,
                        "15757855271",
                        CaesarStringDealTool.MD5("123456")
                    )
                )
            }
            if (result?.data?.isCorrectCode()!!) {
                ValueUserData.setUserInfo(result.data?.uuid, result.data?.token)
            }
        }

    }
}