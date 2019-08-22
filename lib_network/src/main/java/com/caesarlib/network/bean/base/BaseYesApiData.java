package com.caesarlib.network.bean.base;

/**
 * created by Caesar on 2019/5/16
 * email : 15757855271@163.com
 */
public abstract class BaseYesApiData {
    protected int err_code;
    protected String err_msg;

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

}
