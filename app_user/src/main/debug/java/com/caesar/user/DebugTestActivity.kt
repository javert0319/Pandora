package com.caesar.user

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.caesar.user.databinding.UserActivityDebugTestBinding
import com.caesarlib.fram.view.BaseActivity
import com.caesarlib.fram.view.BaseView
import com.caesarlib.res_tools.CSLog
import kotlinx.android.synthetic.main.user_activity_debug_test.*

@Route(path = "/user/test")
class DebugTestActivity : BaseActivity<BaseView, DebugTestViewModel>() {
    override fun createViewModel(): DebugTestViewModel {
        return DebugTestViewModel()
    }

    override fun onFirstResume() {
        mViewModel?.load()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<UserActivityDebugTestBinding>(this, R.layout.user_activity_debug_test)
        binding.vm = mViewModel
        initToorBar("测试")
    }


}
