package com.caesarlib.network.bean.base

/**
 * 小白接口数据通用的基类
 * created by Caesar on 2019/3/27
 * email : 15757855271@163.com
 */
class BaseYesApiBean<T> {
    var ret: Int = 0
    var msg: String? = null
    var data: T? = null

    val isSuccess: Boolean
        get() = ret == 200

}
