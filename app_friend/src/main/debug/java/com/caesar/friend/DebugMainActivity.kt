package com.caesar.friend

import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.alibaba.android.arouter.launcher.ARouter
import com.caesarlib.fram.view.BaseSimpleActivity

class DebugMainActivity : BaseSimpleActivity() {
    override fun onFirstResume() {
//        NetFacede.getInstance().defaultService.userLogin(
//            ParamsFactary.userLoginARegisterParam(
//                YesApiServiceName.LOGIN,
//                "15757855271",
//                CaesarStringDealTool.MD5("123456")
//            )
//        ).subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                if (it.data.err_code == 0) {
//                    ValueUserData.setUserInfo(it.data.uuid, it.data.token)
//                }
//            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.friend_activity_debug_main)
        initToorBar("用户模块", false)
        findViewById<AppCompatButton>(R.id.btn_test).setOnClickListener {
            ARouter.getInstance().build("/user/test").navigation()
        }
        findViewById<AppCompatButton>(R.id.btn_login).setOnClickListener {
            ARouter.getInstance().build("/user/login").navigation()
        }
        findViewById<AppCompatButton>(R.id.btn_register).setOnClickListener {
            ARouter.getInstance().build("/user/register").greenChannel().navigation()
        }
        findViewById<AppCompatButton>(R.id.btn_reset_password).setOnClickListener {
            ARouter.getInstance().build("/user/resetpassword").navigation()
        }
        findViewById<AppCompatButton>(R.id.btn_protocol).setOnClickListener {
            ARouter.getInstance().build("/user/protocol").navigation()
        }
        findViewById<AppCompatButton>(R.id.btn_userinfo).setOnClickListener {
            ARouter.getInstance().build("/user/info").navigation()
        }

    }
}
















