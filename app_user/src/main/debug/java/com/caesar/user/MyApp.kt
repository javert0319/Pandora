package com.caesar.user

import com.billy.android.swipe.SmartSwipeBack
import com.caesarlib.fram.view.MyBaseApplication
import org.koin.core.context.loadKoinModules


class MyApp : MyBaseApplication() {
    override fun onCreate() {
        super.onCreate()
        loadKoinModules(KoinModuleUser.userModule)
        SmartSwipeBack.activitySlidingBack(this,null)
    }
}
