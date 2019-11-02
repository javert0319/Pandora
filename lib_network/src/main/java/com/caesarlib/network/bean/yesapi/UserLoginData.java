package com.caesarlib.network.bean.yesapi;


import com.caesarlib.network.bean.base.BaseYesApiData;

/**
 * 用户登录
 * created by Caesar on 2019/5/16
 * email : 15757855271@163.com
 */
public class UserLoginData extends BaseYesApiData {
    private String uuid;
    private String token;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserLoginData{" +
                "uuid='" + uuid + '\'' +
                ", token='" + token + '\'' +
                ", err_code=" + err_code +
                ", err_msg='" + err_msg + '\'' +
                '}';
    }
}
