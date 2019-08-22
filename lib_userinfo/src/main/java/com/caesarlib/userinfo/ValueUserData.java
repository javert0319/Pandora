package com.caesarlib.userinfo;

/**
 * created by Caesar on 2019/5/25 0025
 * email : 15757855271@163.com
 */
public class ValueUserData {
    private static String UserUuid = "";
    private static String UserToken = "";

    public static void setUserInfo(String userUuid, String userToken){
        UserUuid = userUuid;
        UserToken = userToken;
    }
    public static String getUserUuid() {
        return UserUuid;
    }


    public static String getUserToken() {
        return UserToken;
    }

}
