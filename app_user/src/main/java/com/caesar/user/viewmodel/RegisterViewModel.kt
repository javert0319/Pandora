package com.caesar.user.viewmodel

import android.content.Context
import android.content.Intent
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.afollestad.materialdialogs.MaterialDialog
import com.alibaba.android.arouter.launcher.ARouter
import com.caesar.user.R
import com.caesarlib.fram.global.FramGroble
import com.caesarlib.fram.view.BaseView
import com.caesarlib.fram.viewmodel.BaseViewModel
import com.caesarlib.network.ParamsFactary
import com.caesarlib.network.YesApiServiceName
import com.caesarlib.res_tools.AppNormalTool
import com.caesarlib.res_tools.CaesarStringDealTool
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * created by Caesar on 2019/4/12
 * email : 15757855271@163.com
 */
class RegisterViewModel : BaseViewModel<BaseView>() {
    var phoneNum: String = ""
    var password: String = ""
    var passwordCheck: String = ""
    var checkNum: String = ""
    var captcha: String? = ""
    var decode64 = ObservableField<String>("")

    fun goLogin() {
        ARouter.getInstance().build("/user/login").navigation()
    }

    fun goProtocol() {
        ARouter.getInstance().build("/user/protocol").navigation()
    }

    fun onCheckNum() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = saveApiCall {
                getDefaultApiService()?.CaptchaCreate(
                    ParamsFactary.createCaptchaParam()
                )
            }
            if (0 == result?.data?.err_code) {
                captcha = result.data?.captcha_id
                decode64.set(result.data?.captcha_img)
            }
        }
    }

    fun checkData() {
        when {
            CaesarStringDealTool.StrsIsContainNull(phoneNum, password, passwordCheck) -> AppNormalTool.showToast(
                FramGroble.getApp(),
                FramGroble.getValueString(R.string.res_tools_user_pass_none)
            )
            password != passwordCheck -> AppNormalTool.showToast(
                FramGroble.getApp(),
                FramGroble.getValueString(R.string.res_tools_sign_check_password)
            )
            CaesarStringDealTool.StringIsNull(checkNum) -> AppNormalTool.showToast(
                FramGroble.getApp(),
                FramGroble.getValueString(R.string.res_tools_input_check_number)
            )
            else -> viewModelScope.launch(Dispatchers.IO) {
                val result = saveApiCall {
                    getDefaultApiService()?.CaptchaVerify(ParamsFactary.verifyCaptchaParam(checkNum, captcha))
                }
                if (0 == result?.data?.err_code) {
                    goRegister()
                } else {
                    onCheckNum()
                    launch(Dispatchers.Main){
                        AppNormalTool.showToast(FramGroble.getApp(), result?.data?.err_msg)
                    }
                }
            }

        }
    }

    fun goRegister() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = saveApiCall {
                getDefaultApiService()?.userRegister(
                    ParamsFactary.userLoginARegisterParam(
                        YesApiServiceName.REGISTER,
                        phoneNum,
                        CaesarStringDealTool.MD5(password)
                    )
                )
            }
            if (result?.data?.err_code == 0) {
                launch(Dispatchers.Main){
                    MaterialDialog.Builder(FramGroble.getTopActivity() as Context)
                        .title(R.string.res_tools_tips)
                        .content(
                            (FramGroble.getValueString(R.string.res_tools_register_success) as String) + (FramGroble.getValueString(
                                R.string.res_tools_confirm_to_login
                            ) as String)
                        )
                        .positiveText(FramGroble.getValueString(R.string.res_tools_confirm) as String)
                        .cancelable(false)
                        .onPositive { _, _ ->
                            val userIntent = Intent()
                            userIntent.putExtra("username", phoneNum)
                            userIntent.putExtra("password", password)
                            FramGroble.getTopActivity()?.setResult(24, userIntent)
                            FramGroble.getTopActivity()?.finish()
                        }
                        .show()
                }

            } else {
                onCheckNum()
                launch(Dispatchers.Main){
                    result?.data?.err_msg?.let {
                        MaterialDialog.Builder(FramGroble.getTopActivity() as Context)
                            .title(R.string.res_tools_register_fail)
                            .content(it).positiveText(FramGroble.getValueString(R.string.res_tools_confirm) as String)
                            .show()
                    }

                }

            }
        }


    }

}
