package com.caesar.user

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.caesarlib.res_tools.CSLog

/**
 * 登录拦截器
 * created by Caesar on 2019/5/21
 * email : 15757855271@163.com
 */
@Interceptor(priority = 5)
class LoginInterceptor : IInterceptor {


    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        CSLog.I("登录拦截器收到了拦截")
        callback.onContinue(postcard)
    }

    override fun init(context: Context) {
        CSLog.I("登录拦截器初始化")
    }
}
