package com.caesarlib.res_tools

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.widget.Toast


/**
 * describe:app其他的一些工具
 * author: jihan
 * date: 2017/1/26.
 */

object AppNormalTool {
    val PERMISSION_REQUEST = 24

    /**
     * 获取app版本号
     *
     * @return 版本号
     */
    fun getVersionCode(context: Context): Int {
        val pm = context.packageManager
        try {
            val pi = pm.getPackageInfo(context.packageName, 0)
            return pi.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return 1
    }

    /**
     * 跳转权限管理界面
     *
     * @param activity 页面
     */
    fun goPermissionSettingPage(activity: Activity) {
        val uri = Uri.parse("package:" + activity.packageName)//包名
        val intent = Intent("android.settings.APPLICATION_DETAILS_SETTINGS", uri)
        activity.startActivityForResult(intent, PERMISSION_REQUEST)
    }

    /**
     * 判断网络是否连接
     *
     * @return 结果
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm != null) {
            //如果仅仅是用来判断网络连接
            val info = cm.allNetworkInfo
            if (info != null) {
                for (i in info.indices) {
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
        }
        return false
    }


    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue （DisplayMetrics类中属性density）
     * @return
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue （DisplayMetrics类中属性density）
     * @return
     */
    fun dip2px(context: Context, dipValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * 通用的吐司
     *
     * @param context 上下文
     * @param msg     内容
     */
    fun showToast(context: Context?, msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * 通用的吐司
     *
     * @param msg     内容
     */
    fun showToast(msg: String?) {
        showToast(ToolsGroble.appContext, msg)
    }

    /**
     * 获取value中的string值
     *
     * @param context
     * @param value
     * @return
     */
    fun getValueString(context: Context, value: Int): String {
        return context.getString(value)
    }


}
