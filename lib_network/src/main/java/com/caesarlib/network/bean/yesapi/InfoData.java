package com.caesarlib.network.bean.yesapi;


import com.caesarlib.network.bean.base.BaseYesApiData;

/**
 * created by Caesar on 2019/6/4
 * email : 15757855271@163.com
 */
public class InfoData extends BaseYesApiData {
    private UserInfoData info;

    public UserInfoData getInfo() {
        return info;
    }

    public void setInfo(UserInfoData info) {
        this.info = info;
    }
}
