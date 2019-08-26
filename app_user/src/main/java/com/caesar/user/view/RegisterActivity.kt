package com.caesar.user.view

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.caesar.user.R
import com.caesar.user.databinding.UserActivityRegisterBinding
import com.caesar.user.viewmodel.RegisterViewModel
import com.caesarlib.fram.view.BaseActivity
import com.caesarlib.res_tools.AppNormalTool

@Route(path = "/user/register")
class RegisterActivity : BaseActivity<RegisterView, RegisterViewModel>(), RegisterView {
    override fun onFirstResume() {
    }

    private lateinit var binding: UserActivityRegisterBinding

    override fun onCaptchaRetuen(src: String) {
        val decod = Base64.decode(src, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decod, 0, decod.size)
        binding.resToolsView8.setImageBitmap(bitmap)
    }

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
        val layoutParams = binding.resToolsView3.layoutParams as ConstraintLayout.LayoutParams
        if (isPopup) {
            layoutParams.topMargin = AppNormalTool.dip2px(this, 10f)
        } else {
            layoutParams.topMargin = AppNormalTool.dip2px(this, 90f)
        }
        binding.resToolsView3.layoutParams = layoutParams
    }

}
