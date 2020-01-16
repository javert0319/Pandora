package com.caesarlib.network.bean.yesapi

import com.google.gson.Gson

/**
 * 用户扩展信息
 * created by Caesar on 2019/5/29
 * email : 15757855271@163.com
 */
class ExtInfoData {
    var nickName: String? = null
    var age: Int = 0
    var sex: Int = 0
    var province: String? = null
    var city: String? = null
    var area: String? = null
    var headImg: String? = null


    fun toJson(): String {
        return Gson().toJson(this)
    }

}
