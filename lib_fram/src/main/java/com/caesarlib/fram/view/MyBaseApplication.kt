package com.lib.fram.view

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.caesarlib.fram.BuildConfig
import com.caesarlib.res_tools.ToolsGroble
import com.lib.fram.groble.FramGroble

/**
 * application的父类
 * created by Caesar on 2019/5/14
 * email : 15757855271@163.com
 */
open class MyBaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FramGroble.setApp(this)
        ToolsGroble.setAppContext(this)
        registerActivityLifecycleCallbacks(mActivityLifecycleCall)
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    override fun attachBaseContext(base: Context?) {
        MultiDex.install(this)
        super.attachBaseContext(base)
    }

    private val mActivityLifecycleCall = object : Application.ActivityLifecycleCallbacks {

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

        }

        override fun onActivityStarted(activity: Activity) {

        }

        override fun onActivityResumed(activity: Activity) {
            FramGroble.setTopActivity(activity)
        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
        }
    }
}
