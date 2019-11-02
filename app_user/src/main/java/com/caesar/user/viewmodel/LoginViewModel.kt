package com.caesar.user.viewmodel

import androidx.databinding.ObservableField
import com.alibaba.android.arouter.launcher.ARouter
import com.caesar.user.R
import com.caesarlib.fram.groble.FramGroble
import com.caesarlib.fram.view.BaseView
import com.caesarlib.fram.viewmodel.BaseViewModel
import com.caesarlib.res_tools.AppNormalTool
import com.caesarlib.res_tools.CaesarStringDealTool

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
//                NetFacede.getInstance().defaultService.userLogin(
//                    ParamsFactary.userLoginARegisterParam(
//                        YesApiServiceName.LOGIN,
//                        userName.get(),
//                        CaesarStringDealTool.MD5(passWord.get())
//                    )
//                ).subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        if (it.data.err_code == 0) {
//                            ValueUserData.setUserInfo(it.data.uuid,it.data.token)
//                            FramGroble.getTopActivity()?.finish()
//                        }
//                    }
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
