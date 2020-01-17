package com.caesarlib.network.bean.base

/**
 * created by Caesar on 2019/5/16
 * email : 15757855271@163.com
 */
abstract class BaseYesApiData {
    var err_code: Int? = -1
    var err_msg: String? = null
    fun isCorrectCode(): Boolean {
        return err_code == 0
    }
}
