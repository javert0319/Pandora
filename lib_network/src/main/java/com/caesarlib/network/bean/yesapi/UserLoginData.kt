package com.caesarlib.network.bean.yesapi


import com.caesarlib.network.bean.base.BaseYesApiData

/**
 * 用户登录
 * created by Caesar on 2019/5/16
 * email : 15757855271@163.com
 */
class UserLoginData : BaseYesApiData() {
    var uuid: String? = null
    var token: String? = null

    override fun toString(): String {
        return "UserLoginData{" +
                "uuid='" + uuid + '\''.toString() +
                ", token='" + token + '\''.toString() +
                ", err_code=" + err_code +
                ", err_msg='" + err_msg + '\''.toString() +
                '}'.toString()
    }
}
