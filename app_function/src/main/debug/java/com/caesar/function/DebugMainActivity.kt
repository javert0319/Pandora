package com.caesar.function

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
        setContentView(R.layout.function_activity_debug_main)
        initToorBar("功能模块", false)
        findViewById<AppCompatButton>(R.id.btn_joke).setOnClickListener {
            ARouter.getInstance().build("/function/joke").navigation()
        }
        findViewById<AppCompatButton>(R.id.btn_weather).setOnClickListener {
            ARouter.getInstance().build("/function/seven_weather").navigation()
        }

    }
}
















