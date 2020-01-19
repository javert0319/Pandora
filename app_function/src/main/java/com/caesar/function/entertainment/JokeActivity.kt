package com.caesar.function.entertainment

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.caesar.function.R
import com.caesar.function.databinding.FunctionActivityJokeBinding
import com.caesarlib.fram.view.BaseActivity
import com.caesarlib.fram.view.BaseView

@Route(path = "/function/joke")
class JokeActivity : BaseActivity<BaseView, JokeViewModel>() {
    override fun createViewModel(): JokeViewModel {
        return JokeViewModel()
    }

    override fun onFirstResume() {
        mViewModel?.reload()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<FunctionActivityJokeBinding>(this, R.layout.function_activity_joke)
        binding.vm = mViewModel
        initToorBar(getString(R.string.res_tools_joke))
    }
}
