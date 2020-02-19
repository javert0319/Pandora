package com.caesar.pandora.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.caesar.pandora.R
import com.caesar.pandora.databinding.ActivitySplashBinding
import com.caesarlib.fram.view.BaseActivity
import com.caesarlib.fram.view.BaseView
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ktx.immersionBar
import org.koin.androidx.viewmodel.ext.android.getViewModel

@Route(path = "/main/splash")
class SplashActivity : BaseActivity<BaseView, SplashViewModel>() {
    override fun createViewModel(): SplashViewModel {
        return getViewModel()
    }

    override fun onFirstResume() {
        mViewModel?.startTime()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val binding = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
        binding.vm = mViewModel
        immersionBar {
            hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
        }
    }


}
