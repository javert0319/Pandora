package com.caesarlib.res_tools

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
object SharedPreferencesTool {
    private val sp = ToolsGroble.appContext.getSharedPreferences("Pandora_Sp", Context.MODE_PRIVATE)

    private fun edit(): SharedPreferences.Editor = sp.edit()

    val theLocalCiry = "THE_LOCATION_CITY"

    fun getLocationCity(): String? = sp.getString(theLocalCiry, null)

    fun setLocationCity(city: String?) = edit().putString(theLocalCiry, city).commit()


}