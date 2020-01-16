package com.caesarlib.network.bean.yesapi


import com.caesarlib.network.bean.base.BaseYesApiData

/**
 * 验证码
 * created by Caesar on 2019/5/16
 * email : 15757855271@163.com
 */
class CaptchaData : BaseYesApiData() {
    var captcha_id: String? = null
    var captcha_img: String? = null
}
