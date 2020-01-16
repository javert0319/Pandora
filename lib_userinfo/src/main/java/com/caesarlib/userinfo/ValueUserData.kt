package com.caesarlib.userinfo

/**
 * created by Caesar on 2019/5/25 0025
 * email : 15757855271@163.com
 */
object ValueUserData {
    var userUuid: String? = ""
    var userToken: String? = ""

    fun setUserInfo(userUuid: String?, userToken: String?) {
        this.userUuid = userUuid
        this.userToken = userToken
    }

}
