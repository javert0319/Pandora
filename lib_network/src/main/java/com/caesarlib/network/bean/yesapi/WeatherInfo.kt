package com.caesarlib.network.bean.yesapi

import androidx.databinding.ObservableField

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
class WeatherInfo {

    var day: String? = null
    var date: String? = null
    var time: String? = null
    var city: String? = null
    var visibility: String? = null
    var weather: String? = null
    var tem: String? = null
    var win: Any? = null
    var win_speed: String? = null
    var win_meter: String? = null
    var humidity: String? = null
    var pressure: String? = null
    var air: String? = null
    var air_pm25: String? = null
    var air_level: String? = null
    var air_tips: String? = null
    var tem1: String? = null
    var tem2: String? = null
    var wea: String? = null
    var hours: ArrayList<WeatherHours>? = null

    var selectTag = ObservableField<Boolean>(true)
}