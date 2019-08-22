package com.caesar.pandora

import com.caesarlib.fram.view.MyBaseApplication
import com.squareup.leakcanary.LeakCanary

class MyApp : MyBaseApplication() {
    override fun onCreate() {
        super.onCreate()
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this)
        }
    }
}
