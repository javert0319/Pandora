package com.caesar.user.view

import android.content.Intent
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.caesar.user.R
import com.caesar.user.databinding.UserActivityLoginBinding
import com.caesar.user.viewmodel.LoginViewModel
import com.caesarlib.fram.view.BaseActivity
import com.caesarlib.fram.view.BaseView
import com.caesarlib.res_tools.AppNormalTool
import kotlinx.android.synthetic.main.user_activity_login.*

@Route(path = "/user/login")
class LoginActivity : BaseActivity<BaseView, LoginViewModel>() {
    override fun onFirstResume() {
    }

    private lateinit var binding: UserActivityLoginBinding
    override fun createViewModel(): LoginViewModel {
        return LoginViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.user_activity_login)
        binding.model = mViewModel
        initToorBar(getString(R.string.res_tools_login), true, true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            23 -> {
                if (resultCode == 24) {
                    mViewModel?.userName?.set(data?.getStringExtra("username"))
                    mViewModel?.passWord?.set(data?.getStringExtra("password"))
                }
            }
        }

    }

    override fun onKeyboardChange(isPopup: Boolean, keyboardHeight: Int) {
        val layoutParams = res_tools_view1.layoutParams as ConstraintLayout.LayoutParams
        if (isPopup) {
            layoutParams.topMargin = AppNormalTool.dip2px( 10f)
        } else {
            layoutParams.topMargin = AppNormalTool.dip2px( 120f)
        }
        res_tools_view1.layoutParams = layoutParams

    }
}
