package com.caesar.pandora.homepage.fragment

import com.caesar.pandora.BR
import com.caesar.pandora.R
import com.caesar.pandora.homepage.fragment.bean.HomeNorTypeData
import com.caesar.pandora.homepage.fragment.bean.HomeWeatherData
import com.caesarlib.brvahbinding.CSBravhItemBinding
import com.caesarlib.fram.view.BaseView
import com.caesarlib.fram.viewmodel.CSBrvahViewModel
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel : CSBrvahViewModel<BaseView, MultiItemEntity>() {
    override fun getItemBinding(): HashMap<Int, CSBravhItemBinding<Any>> {
        val map = HashMap<Int, CSBravhItemBinding<Any>>()
        map.put(0, CSBravhItemBinding(BR.data, R.layout.layout_item_home_fuction))
        map.put(1, CSBravhItemBinding(BR.data, R.layout.layout_item_home_joke))
        map.put(2, CSBravhItemBinding(BR.data, R.layout.layout_item_home_weather))
        return map
    }

    override fun load() {
        load(loadHomeData())
    }


    override fun attachView(view: BaseView?) {
        super.attachView(view)
        setSpan(BaseQuickAdapter.SpanSizeLookup { _, p1 ->
            if (items[p1]?.itemType == 0) {
                1
            } else {
                3
            }
        })


    }

    fun loadHomeData(): Flowable<List<MultiItemEntity>> {

        return Flowable.create({
            val das = ArrayList<MultiItemEntity>()
            val norD = HomeNorTypeData("今日的笑话", 1)
            das.add(norD)
            val wD = HomeWeatherData("地区", "温度", "风速")
            das.add(wD)
            das.add(HomeNorTypeData("功能1"))
            das.add(HomeNorTypeData("功能2"))
            das.add(HomeNorTypeData("功能3"))
            das.add(HomeNorTypeData("功能4"))
            it.onNext(das)
            it.onComplete()
        }, BackpressureStrategy.BUFFER)
    }


}