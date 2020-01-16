package com.caesarlib.network

import android.os.Build
import android.util.ArrayMap

import java.util.HashMap

/**
 * 网络请求门面
 * created by Caesar on 2019/4/4
 * email : 15757855271@163.com
 */
class NetFacede private constructor() {

    private var serviceMap: MutableMap<String, Any>? = null

    val defaultService: ApiService?
        @Synchronized get() = getService(MyHttpUrl.Url_YesAPI, ApiService::class.java)

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            serviceMap = ArrayMap()
        } else {
            serviceMap = HashMap()
        }
    }

    private object NetFacedeInstance {
        internal val INSTANCE = NetFacede()
    }

    @Synchronized
    private fun <T> getService(baseUrl: String, clz: Class<T>): T? {
        var service =  serviceMap?.get(baseUrl) as T
        if (null == service) {
            service = RetrofitUtil.instance.createRetrofit(baseUrl)!!.create(clz)
            serviceMap?.put(baseUrl,service as Any)
        }
        return service
    }

    companion object {

        val instance: NetFacede
            get() = NetFacedeInstance.INSTANCE
    }

}
