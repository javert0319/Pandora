package com.caesarlib.network.bean.yesapi;


import com.caesarlib.network.bean.base.BaseYesApiData;

/**
 * 验证码
 * created by Caesar on 2019/5/16
 * email : 15757855271@163.com
 */
public class CaptchaData extends BaseYesApiData {
    private String captcha_id;
    private String captcha_img;

    public String getCaptcha_id() {
        return captcha_id;
    }

    public void setCaptcha_id(String captcha_id) {
        this.captcha_id = captcha_id;
    }

    public String getCaptcha_img() {
        return captcha_img;
    }

    public void setCaptcha_img(String captcha_img) {
        this.captcha_img = captcha_img;
    }
}
