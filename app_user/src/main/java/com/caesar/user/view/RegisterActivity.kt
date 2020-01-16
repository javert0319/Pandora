package com.caesar.user.view

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.caesar.user.R
import com.caesar.user.databinding.UserActivityRegisterBinding
import com.caesar.user.viewmodel.RegisterViewModel
import com.caesarlib.fram.view.BaseActivity
import com.caesarlib.fram.view.BaseView
import com.caesarlib.res_tools.AppNormalTool
import kotlinx.android.synthetic.main.user_activity_register.*

@Route(path = "/user/register")
class RegisterActivity : BaseActivity<BaseView, RegisterViewModel>() {
    override fun onFirstResume() {
    }

    private lateinit var binding: UserActivityRegisterBinding

    override fun createViewModel(): RegisterViewModel {
        return RegisterViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.user_activity_register)
        binding.model = mViewModel
        initToorBar(getString(R.string.res_tools_register), true, true)
    }

    override fun onResume() {
        super.onResume()
        mViewModel?.onCheckNum()
    }

    override fun onKeyboardChange(isPopup: Boolean, keyboardHeight: Int) {
        val layoutParams = res_tools_view3.layoutParams as ConstraintLayout.LayoutParams
        if (isPopup) {
            layoutParams.topMargin = AppNormalTool.dip2px(this, 10f)
        } else {
            layoutParams.topMargin = AppNormalTool.dip2px(this, 90f)
        }
       res_tools_view3.layoutParams = layoutParams
    }

}
