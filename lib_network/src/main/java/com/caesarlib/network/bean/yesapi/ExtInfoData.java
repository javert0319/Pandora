package com.caesarlib.network.bean.yesapi;

import com.google.gson.Gson;

/**
 * 用户扩展信息
 * created by Caesar on 2019/5/29
 * email : 15757855271@163.com
 */
public class ExtInfoData {
    private String nickName;
    private int age;
    private int sex;
    private String province;
    private String city;
    private String area;
    private String headImg;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String toJson() {
        return new Gson().toJson(this);
    }

}
