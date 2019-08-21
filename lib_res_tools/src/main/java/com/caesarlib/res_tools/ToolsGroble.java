package com.caesarlib.res_tools;

import android.app.Application;

/**
 * created by Caesar on 2019/6/1 0001
 * email : 15757855271@163.com
 */
public class ToolsGroble {
    private static Application application;

    public static void setAppContext(Application appContext) {
      application = appContext;
    }

    public static Application getAppContext() {
        return application;
    }
}
