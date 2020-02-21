package com.caesar.pandora

import com.billy.android.swipe.SmartSwipeBack
import com.caesar.function.KoinModuleFunction
import com.caesar.function.weather.WeatherSevenActivity
import com.caesar.pandora.homepage.fragment.HomeViewModel
import com.caesar.pandora.main.MainActivity
import com.caesar.pandora.main.MainViewModel
import com.caesar.pandora.splash.SplashActivity
import com.caesar.pandora.splash.SplashViewModel
import com.caesar.user.KoinModuleUser
import com.caesarlib.fram.view.MyBaseApplication
import com.squareup.leakcanary.LeakCanary
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class MyApp : MyBaseApplication() {
    override fun onCreate() {
        super.onCreate()
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this)
        }
        loadKoinModules(appModule,KoinModuleUser.userModule,KoinModuleFunction.functionModule)
        SmartSwipeBack.activitySlidingBack(this) {
            return@activitySlidingBack !(it is SplashActivity || it is MainActivity||it is WeatherSevenActivity)
        }
    }

    val appModule = module {
        viewModel {
            SplashViewModel()
        }
        viewModel {
            MainViewModel()
        }
        viewModel {
            HomeViewModel()
        }
    }

}
