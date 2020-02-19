package com.caesar.function

import com.caesarlib.fram.view.MyBaseApplication
import org.koin.core.context.loadKoinModules


class MyApp : MyBaseApplication() {
    override fun onCreate() {
        super.onCreate()
        loadKoinModules(KoinModuleFunction.functionModule)
    }
}
