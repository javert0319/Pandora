package com.caesarlib.network.bean.yesapi;


import com.caesarlib.network.bean.base.BaseYesApiData;

/**
 * created by Caesar on 2019/6/4
 * email : 15757855271@163.com
 */
public class UserInfoData extends BaseYesApiData {
    private String uuid;
    private String username;
    private String role;
    private String rolename;
    private String register_time;
    private String register_ip;
    private ExtInfoData ext_info;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }

    public String getRegister_ip() {
        return register_ip;
    }

    public void setRegister_ip(String register_ip) {
        this.register_ip = register_ip;
    }

    public ExtInfoData getExt_info() {
        return ext_info;
    }

    public void setExt_info(ExtInfoData ext_info) {
        this.ext_info = ext_info;
    }
}
