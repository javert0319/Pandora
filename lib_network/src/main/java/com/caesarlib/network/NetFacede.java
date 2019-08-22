package com.caesarlib.network;

import android.os.Build;
import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求门面
 * created by Caesar on 2019/4/4
 * email : 15757855271@163.com
 */
public class NetFacede {

    private Map<String, Object> serviceMap;

    private NetFacede() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            serviceMap = new ArrayMap<>();
        } else {
            serviceMap = new HashMap<>();
        }
    }

    private static class NetFacedeInstance {
        private static final NetFacede INSTANCE = new NetFacede();
    }

    public static NetFacede getInstance() {
        return NetFacedeInstance.INSTANCE;
    }

    public synchronized ApiService getDefaultService() {
        return getService(MyHttpUrl.Url_YesAPI, ApiService.class);
    }

    private synchronized <T> T getService(String baseUrl, Class clz) {
        T service = (T) serviceMap.get(baseUrl);
        if (null == service) {
            service = (T) RetrofitUtil.getInstance().createRetrofit(baseUrl).create(clz);
            serviceMap.put(baseUrl, service);
        }
        return service;
    }

}
