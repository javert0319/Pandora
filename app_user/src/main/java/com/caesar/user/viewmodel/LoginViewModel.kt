package com.caesar.user.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import com.caesar.user.R
import com.caesarlib.fram.global.FramGroble
import com.caesarlib.fram.view.BaseView
import com.caesarlib.fram.viewmodel.BaseViewModel
import com.caesarlib.network.ParamsFactary
import com.caesarlib.network.YesApiServiceName
import com.caesarlib.res_tools.AppNormalTool
import com.caesarlib.res_tools.CaesarStringDealTool
import com.caesarlib.userinfo.ValueUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * created by Caesar on 2019/4/8
 * email : 15757855271@163.com
 */
class LoginViewModel : BaseViewModel<BaseView>() {
    var userName = ObservableField<String>()
    var passWord = ObservableField<String>()

    fun onBtnClick() {
        when {
            CaesarStringDealTool.StrsIsContainNull(userName.get(), passWord.get()) -> AppNormalTool.showToast(
                FramGroble.getApp(),
                FramGroble.getValueString(R.string.res_tools_user_pass_none)
            )
            else -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val result = saveApiCall {
                        getDefaultApiService()?.userLogin(
                            ParamsFactary.userLoginARegisterParam(
                                YesApiServiceName.LOGIN,
                                userName.get(),
                                CaesarStringDealTool.MD5(passWord.get())
                            )
                        )
                    }
                    if (result?.data?.err_code==0) {
                        ValueUserData.setUserInfo(result.data?.uuid, result.data?.token)
                        launch(Dispatchers.Main){
                            FramGroble.getTopActivity()?.finish()
                        }
                    }
                }

            }
        }
    }

    fun goRegister() {
        ARouter.getInstance().build("/user/register").navigation(FramGroble.getTopActivity(), 23)
    }

    fun goResetPassword() {
        ARouter.getInstance().build("/user/resetpassword").navigation()
    }
}
