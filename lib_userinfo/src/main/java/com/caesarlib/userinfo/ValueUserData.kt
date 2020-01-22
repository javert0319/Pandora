package com.caesarlib.userinfo

import androidx.databinding.ObservableField

/**
 * created by Caesar on 2019/5/25 0025
 * email : 15757855271@163.com
 */
object ValueUserData {
    var userUuid = ObservableField<String>("")
    var userToken =ObservableField<String>("")
    var nickName = ObservableField<String>("")
    var avatarImg = ObservableField<String>("")
    var province = ObservableField<String>("")
    var city = ObservableField<String>("")
    var area = ObservableField<String>("")


    fun setUserInfo(userUuid: String?, userToken: String?) {
        this.userUuid.set(userUuid)
        this.userToken.set(userToken)
    }

}
