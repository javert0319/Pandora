package com.caesarlib.network

/**
 * YesApi接口名字
 * created by Caesar on 2019/5/20
 * email : 15757855271@163.com
 */
object YesApiServiceName {
    const val REGISTER = "App.User.Register"//注册
    const val LOGIN = "App.User.Login"//登陆
    const val CAPTCREATE = "App.Captcha.Create"//生成验证码
    const val CAPTVERIFY = "App.Captcha.Verify"//校对验证码
    const val PROFILE = "App.User.Profile"//会员个人资料
    const val UPDATAEXTINFO = "App.User.UpdateExtInfo"//修改会员扩展信息
    const val UPLOADIMG = "App.CDN.UploadImg"//上传图片
    const val DeleteFile = "App.CDN.Delete"//删除图片或文件
    const val OKIJOKE = "App.Common_Joke.RandOne"//随机一个笑话
    const val LiveWeather = "App.Common_Weather.LiveWeather"//地区实时天气状况
}
