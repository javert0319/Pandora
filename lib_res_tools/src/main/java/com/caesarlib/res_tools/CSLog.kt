package com.caesarlib.res_tools

import android.util.Log

/**
 * describe:日志输出的工具类
 * author: jihan
 * date: 2017/1/20.
 */

object CSLog {
    private const val TAG = "CaesarLog"

    /**
     * 输出I级别的日志(日志较短)
     *
     * @param msg 内容
     */
    fun d(msg: String?) {
        Log.d(TAG, msg)
    }

    /**
     * 输出I级别的日志(日志可长可段)
     *
     * @param flag 标识
     * @param msg  内容
     */
    fun d(flag: String?, msg: String) {
        if (CaesarStringDealTool.getStringLength(msg) > 3000) {
            d(flag, "该log的长度:" + CaesarStringDealTool.getStringLength(msg))
            val chunkCount = CaesarStringDealTool.getStringLength(msg) / 3000
            for (i in 0..chunkCount) {
                val max = 3000 * (i + 1)
                if (max >= CaesarStringDealTool.getStringLength(msg)) {
                    Log.d(
                        TAG + "flag:" +
                                flag,
                        "chunk " + i + " of " + chunkCount + ":" + msg?.substring(3000 * i)
                    )
                } else {
                    Log.d(
                        TAG +
                                "flag:" +
                                flag,
                        "chunk " + i + " of " + chunkCount + ":" + msg?.substring(3000 * i, max)
                    )
                }
            }
        } else {
            Log.d(TAG + "flag:" + flag, msg)
        }
    }

}
