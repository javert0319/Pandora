package com.caesar.user.viewmodel

import android.content.Context
import android.content.Intent
import com.afollestad.materialdialogs.MaterialDialog
import com.alibaba.android.arouter.launcher.ARouter
import com.caesar.user.R
import com.caesar.user.view.RegisterView
import com.caesarlib.fram.groble.FramGroble
import com.caesarlib.fram.viewmodel.BaseViewModel
import com.caesarlib.network.NetFacede
import com.caesarlib.network.ParamsFactary
import com.caesarlib.network.YesApiServiceName
import com.caesarlib.res_tools.AppNormalTool
import com.caesarlib.res_tools.CaesarLogTool
import com.caesarlib.res_tools.CaesarStringDealTool
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * created by Caesar on 2019/4/12
 * email : 15757855271@163.com
 */
class RegisterViewModel : BaseViewModel<RegisterView>() {
    var phoneNum: String = ""
    var password: String = ""
    var passwordCheck: String = ""
    var checkNum: String = ""
    var captcha: String = ""

    fun goLogin() {
        ARouter.getInstance().build("/user/login").navigation()
    }

    fun goProtocol() {
        ARouter.getInstance().build("/user/protocol").navigation()
    }

    fun onCheckNum() {
        NetFacede.getInstance().defaultService.CaptchaCreate(ParamsFactary.createCaptchaParam())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (0 == it.data.err_code) {
                    mViewRef?.get()?.onCaptchaRetuen(it.data.captcha_img)
                    captcha = it.data.captcha_id
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
            else -> NetFacede.getInstance().defaultService.CaptchaVerify(ParamsFactary.verifyCaptchaParam(checkNum, captcha))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (0 == it.data.err_code) {
                        goRegister()
                    } else {
                        AppNormalTool.showToast(FramGroble.getApp(), it.data.err_msg)
                        onCheckNum()
                    }
                }

        }
    }

    fun goRegister() {
        NetFacede.getInstance().defaultService.userRegister(
            ParamsFactary.userLoginARegisterParam(
                YesApiServiceName.REGISTER,
                phoneNum,
                CaesarStringDealTool.MD5(password)
            )
        ).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.data.err_code == 0) {
                    MaterialDialog.Builder(FramGroble.getTopActivity() as Context)
                        .title(R.string.res_tools_tips)
                        .content((FramGroble.getValueString(R.string.res_tools_register_success) as String) + (FramGroble.getValueString(R.string.res_tools_confirm_to_login) as String))
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
                } else {
                    onCheckNum()
                    MaterialDialog.Builder(FramGroble.getTopActivity() as Context).title(R.string.res_tools_register_fail)
                        .content(it.data.err_msg).positiveText(FramGroble.getValueString(R.string.res_tools_confirm) as String)
                        .show()
                }
            }, {
                CaesarLogTool.I(FramGroble.getValueString(R.string.res_tools_unknown_error))
            })
    }

}
