package com.caesar.user

import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import com.caesarlib.fram.view.BaseActivity
import com.caesarlib.fram.view.BaseSimpleActivity
import com.caesarlib.fram.view.BaseView
import com.caesarlib.network.NetFacede
import com.caesarlib.network.ParamsFactary
import com.caesarlib.network.YesApiServiceName
import com.caesarlib.res_tools.CSLog
import com.caesarlib.res_tools.CaesarStringDealTool
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DebugMainActivity : BaseActivity<BaseView,DebugMainViewModel>() {
    override fun createViewModel(): DebugMainViewModel {
        return DebugMainViewModel()
    }

    override fun onFirstResume() {
        mViewModel?.doNetTest()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_activity_debug_main)
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
















