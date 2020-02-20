package com.caesar.function.weather

import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.caesar.function.BR
import com.caesar.function.R
import com.caesarlib.brvahbinding.CSBravhItemBinding
import com.caesarlib.brvahbinding.action.CSAction1
import com.caesarlib.brvahbinding.action.CSAction2
import com.caesarlib.customview.citychose.CityChoseDialogWorker
import com.caesarlib.fram.global.FramGroble
import com.caesarlib.fram.view.BaseView
import com.caesarlib.fram.viewmodel.CSBrvahTwoListViewModel
import com.caesarlib.network.ParamsFactary
import com.caesarlib.network.YesApiServiceName
import com.caesarlib.network.bean.yesapi.WeatherHours
import com.caesarlib.network.bean.yesapi.WeatherInfo
import com.caesarlib.res_tools.CaesarStringDealTool
import com.caesarlib.res_tools.SharedPreferencesTool
import com.caesarlib.userinfo.ValueUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.set

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
class WeatherSevenViewModel : CSBrvahTwoListViewModel<BaseView, WeatherInfo, WeatherHours>() {

    val airTip = ObservableField<String>()
    val currDay = ObservableField<String>()
    val hourAir = ObservableField<ArrayList<WeatherHours>>()

    override fun attachView(view: BaseView?) {
        super.attachView(view)
        if (CaesarStringDealTool.StringIsNotNull(ValueUserData.city.get())) {
            titleBarRightTxt.set(ValueUserData.city.get())
        } else {
            titleBarRightTxt.set(FramGroble.getValueString(R.string.res_tools_chose_city))
        }
    }


    override fun getItemBindingA(): HashMap<Int, CSBravhItemBinding<Any>> {
        val map = HashMap<Int, CSBravhItemBinding<Any>>()
        map[0] = CSBravhItemBinding(BR.bean, R.layout.function_item_weather_seven, BR.action, action)
        return map
    }

    override fun getItemBindingB(): HashMap<Int, CSBravhItemBinding<Any>> {
        val map = HashMap<Int, CSBravhItemBinding<Any>>()
        map[0] = CSBravhItemBinding(BR.bean, R.layout.function_item_weather_hour)
        return map
    }

    override fun loadA() {
        if (CaesarStringDealTool.StringIsNotNull(ValueUserData.city.get())) {
            viewModelScope.launch(Dispatchers.IO) {
                val result = saveApiCallA {
                    getDefaultApiService()?.WeekWeather(
                        ParamsFactary.LiveWeather(ValueUserData.city.get(), YesApiServiceName.WeekWeather)
                    )
                }
                if (result?.isSuccess!!) {
                    val currWeather = result.data?.weather?.get(0)
                    currWeather?.selectTag?.set(false)
                    loadA(result.data?.weather)
                    airTip.set(currWeather?.air_tips)
                    currDay.set(currWeather?.date)
                    hourAir.set(currWeather?.hours)
                    reloadB()
                }
            }
        }
    }


    override fun loadB() {
        viewModelScope.launch(Dispatchers.IO) {
            loadB(hourAir.get())
        }
    }

    override fun reloadA() {
        super.reloadA()
        hourAir.set(null)
        airTip.set("")
        reloadB()

    }

    override fun rightTxtClick(view: View) {
        CityChoseDialogWorker.getInstance()
            .showCityChoseDialog((FramGroble.getTopActivity() as AppCompatActivity).fragmentManager, 2) {
                ValueUserData.city.set(it.city)
                titleBarRightTxt.set(it.city)
                SharedPreferencesTool.setLocationCity(it.city)
                reloadA()
            }
    }

    var action = CSAction1< WeatherInfo> {
        for (itemD in itemsA!!) {
            itemD.selectTag.set(true)
        }
        it.selectTag.set(false)
        hourAir.set(it?.hours)
        reloadB()
    }

    override fun onitemDecorationA(): RecyclerView.ItemDecoration? {
        var decor = DividerItemDecoration(FramGroble.getTopActivity(), DividerItemDecoration.HORIZONTAL)
        decor.setDrawable(FramGroble.getTopActivity()?.resources?.getDrawable(R.drawable.res_tools_divider_color_gray) as Drawable)
        return decor
    }
}