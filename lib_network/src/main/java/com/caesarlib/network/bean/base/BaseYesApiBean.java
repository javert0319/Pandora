package com.caesarlib.network.bean.base;

/**
 * 小白接口数据通用的基类
 * created by Caesar on 2019/3/27
 * email : 15757855271@163.com
 */
public class BaseYesApiBean<T> {
    private int ret;
    private String msg;
    private T data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return ret == 200;
    }

}
