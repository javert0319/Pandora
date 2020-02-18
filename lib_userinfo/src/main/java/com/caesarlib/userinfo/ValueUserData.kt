package com.caesarlib.userinfo

import androidx.databinding.ObservableField

/**
 * created by Caesar on 2019/5/25 0025
 * email : 15757855271@163.com
 */
object ValueUserData {
    val userUuid = ObservableField<String>("")
    val userToken = ObservableField<String>("")
    val nickName = ObservableField<String>("")
    val avatarImg = ObservableField<String>("")


    //当前的地址信息
    val province = ObservableField<String>()
    val city = ObservableField<String>()
    val area = ObservableField<String>()


    fun setUserInfo(userUuid: String?, userToken: String?) {
        this.userUuid.set(userUuid)
        this.userToken.set(userToken)
    }

}
