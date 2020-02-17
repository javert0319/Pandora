package com.caesar.pandora

import com.caesarlib.fram.view.MyBaseApplication
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : MyBaseApplication() {
    override fun onCreate() {
        super.onCreate()
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this)
        }
        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
    }


    val appModule = module {

    }
}
