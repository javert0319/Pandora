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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<UserActivityDebugTestBinding>(this, R.layout.user_activity_debug_test)
//        setContentView(R.layout.user_activity_debug_test)
        initToorBar("测试")
        findViewById<Button>(R.id.res_tools_btn_go).setOnClickListener {
                        CSLog.d("nihao")
           mViewModel?.doNetTest()
        }
        res_tools_view1.layoutManager = LinearLayoutManager(this)
        binding.vm = mViewModel
        res_tools_view2.setOnClickListener {
//            res_tools_view1.adapter?.notifyDataSetChanged()
            mViewModel?.name?.set("dhdgdgdg")
        }
        res_tools_view3.setOnClickListener {
            user_textview.text = "123"
        }
        res_tools_view4.setOnClickListener {
            CSLog.d("zhgi:"+ mViewModel?.name?.get())
        }
    }


}
