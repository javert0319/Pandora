package com.caesar.pandora.homepage.fragment.bean

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.caesarlib.customview.citychose.CityChoseDialogWorker
import com.caesarlib.fram.beans.KeyValueSData
import com.caesarlib.fram.global.FramGroble
import com.caesarlib.fram.viewmodel.NormalNetModel
import com.caesarlib.network.ParamsFactary
import com.caesarlib.res_tools.CaesarStringDealTool
import com.caesarlib.res_tools.SharedPreferencesTool
import com.caesarlib.userinfo.ValueUserData
import com.chad.library.adapter.base.entity.MultiItemEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeWeatherData(var viewScope: CoroutineScope) : NormalNetModel(), MultiItemEntity {
    val dataTime = ObservableField<String>()
    val weatherData = ObservableArrayList<KeyValueSData>()
    val weatherTips = ObservableField<String>()

    init {
        getWeather()
        ValueUserData.city.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                SharedPreferencesTool.setLocationCity(ValueUserData.city.get())
                refreshWeather()
            }

        })
    }

    override fun getItemType(): Int {
        return 2
    }

    fun getWeather() {
        viewScope.launch(Dispatchers.IO) {
            if (CaesarStringDealTool.StringIsNotNull(ValueUserData.city.get())) {
                val result = saveApiCall {
                    getDefaultApiService()?.LiveWeather(
                        ParamsFactary.LiveWeather(ValueUserData.city.get())
                    )
                }
                if (result?.isSuccess!!) {
                    val weather = result.data?.weather
                    dataTime.set(weather?.date + "  " + weather?.time)
                    weatherData.clear()
                    weatherData.add(KeyValueSData("天气情况", weather?.weather))
                    weatherData.add(KeyValueSData("温度", weather?.tem + "度"))
                    weatherData.add(KeyValueSData("风向", weather?.win as String))
                    weatherData.add(KeyValueSData("风速等级", weather.win_speed))
                    weatherData.add(KeyValueSData("风速", weather.win_meter))
                    weatherData.add(KeyValueSData("湿度", weather.humidity))
                    weatherData.add(KeyValueSData("气压（hPa）", weather.pressure))
                    weatherData.add(KeyValueSData("空气质量", weather.air))
                    weatherData.add(KeyValueSData("PM2.5", weather.air_pm25))
                    weatherData.add(KeyValueSData("空气质量等级", weather.air_level))
                    weatherTips.set(weather.air_tips)
                }
            } else {

            }
        }
    }

    fun choseCity() {
        CityChoseDialogWorker.getInstance()
            .showCityChoseDialog((FramGroble.getTopActivity() as AppCompatActivity).fragmentManager, 2) {
                ValueUserData.city.set(it.city)
            }
    }

    fun refreshWeather() {
        getWeather()
    }
}
