package com.caesar.pandora

import com.caesar.function.KoinModuleFunction
import com.caesar.pandora.homepage.fragment.HomeViewModel
import com.caesar.pandora.main.MainViewModel
import com.caesar.pandora.splash.SplashViewModel
import com.caesar.user.KoinModuleUser
import com.caesarlib.fram.view.MyBaseApplication
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : MyBaseApplication() {
    override fun onCreate() {
        super.onCreate()
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this)
        }
        loadKoinModules(appModule,KoinModuleUser.userModule,KoinModuleFunction.functionModule)
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
