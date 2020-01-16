package com.caesarlib.network.bean.yesapi


import com.caesarlib.network.bean.base.BaseYesApiData

/**
 * created by Caesar on 2019/6/4
 * email : 15757855271@163.com
 */
class UserInfoData : BaseYesApiData() {
    var uuid: String? = null
    var username: String? = null
    var role: String? = null
    var rolename: String? = null
    var register_time: String? = null
    var register_ip: String? = null
    var ext_info: ExtInfoData? = null
}
