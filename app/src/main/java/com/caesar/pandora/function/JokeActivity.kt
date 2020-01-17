package com.caesar.pandora.function

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.caesar.pandora.R
import com.caesar.pandora.databinding.ActivityJokeBinding
import com.caesarlib.fram.view.BaseActivity
import com.caesarlib.fram.view.BaseView

@Route(path = "/main/joke")
class JokeActivity : BaseActivity<BaseView, JokeViewModel>() {
    override fun createViewModel(): JokeViewModel {
        return JokeViewModel()
    }

    override fun onFirstResume() {
        mViewModel?.reload()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityJokeBinding>(this, R.layout.activity_joke)
        binding.vm = mViewModel
        initToorBar(getString(R.string.res_tools_joke))
    }
}
