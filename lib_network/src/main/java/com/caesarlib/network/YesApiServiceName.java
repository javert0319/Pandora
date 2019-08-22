package com.caesarlib.network;

/**
 * YesApi接口名字
 * created by Caesar on 2019/5/20
 * email : 15757855271@163.com
 */
public class YesApiServiceName {
    public static final String REGISTER = "App.User.Register";//注册
    public static final String LOGIN = "App.User.Login";//登陆
    public static final String CAPTCREATE = "App.Captcha.Create";//生成验证码
    public static final String CAPTVERIFY = "App.Captcha.Verify";//校对验证码
    public static final String PROFILE= "App.User.Profile";//会员个人资料
    public static final String UPDATAEXTINFO= "App.User.UpdateExtInfo";//修改会员扩展信息
    public static final String UPLOADIMG= "App.CDN.UploadImg";//上传图片
    public static final String DeleteFile= "App.CDN.Delete";//删除图片或文件
}
