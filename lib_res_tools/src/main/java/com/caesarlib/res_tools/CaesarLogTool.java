package com.caesarlib.res_tools;

import android.util.Log;

/**
 * describe:日志输出的工具类
 * author: jihan
 * date: 2017/1/20.
 */

public class CaesarLogTool {
    private static final String TAG = "CaesarLog";

    /**
     * 输出I级别的日志(日志较短)
     *
     * @param msg 内容
     */
    public static void I(String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, msg);
        }
    }

    /**
     * 输出I级别的日志(日志可长可段)
     *
     * @param flag 标识
     * @param msg  内容
     */
    public static void I(String flag, String msg) {
        if (!BuildConfig.DEBUG) {
            return;
        }
        if (CaesarStringDealTool.getStringLength(msg) > 3000) {
            I(flag, "该log的长度:" + CaesarStringDealTool.getStringLength(msg));
            int chunkCount = CaesarStringDealTool.getStringLength(msg) / 3000;
            for (int i = 0; i <= chunkCount; i++) {
                int max = 3000 * (i + 1);
                if (max >= CaesarStringDealTool.getStringLength(msg)) {
                    Log.i(TAG + "flag:" +
                            flag, "chunk " + i + " of " + chunkCount + ":" + msg.substring(3000 * i));
                } else {
                    Log.i(TAG +
                            "flag:" +
                            flag, "chunk " + i + " of " + chunkCount + ":" + msg.substring(3000 * i, max));
                }
            }
        } else {
            Log.i(TAG + "flag:" + flag, msg);
        }
    }

}
