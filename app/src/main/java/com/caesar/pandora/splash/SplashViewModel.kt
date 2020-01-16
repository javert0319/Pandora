package com.caesar.pandora.splash

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import com.caesarlib.fram.global.FramGroble
import com.caesarlib.fram.view.BaseView
import com.caesarlib.fram.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel:BaseViewModel<BaseView>() {
    var timeCount = ObservableField<String>()
    //计时开始
     fun startTime() {
        viewModelScope.launch(Dispatchers.IO){
            repeat(2){
                timeCount.set((5-it).toString())
                delay(1000)
            }
            goMain()
        }
    }
    //跳转到mainAct
     fun goMain() {
        ARouter.getInstance().build("/main/main").navigation()
        FramGroble.getTopActivity()?.finish()
    }
}