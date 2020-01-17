package com.caesar.pandora.homepage.fragment

import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.caesar.pandora.BR
import com.caesar.pandora.R
import com.caesar.pandora.homepage.fragment.bean.HomeNorTypeData
import com.caesar.pandora.homepage.fragment.bean.HomeWeatherData
import com.caesarlib.brvahbinding.CSBravhItemBinding
import com.caesarlib.fram.decoration.NormaltemDecoration
import com.caesarlib.fram.view.BaseView
import com.caesarlib.fram.viewmodel.CSBrvahViewModel
import com.caesarlib.res_tools.AppNormalTool
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        loadData()
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



    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val das = ArrayList<MultiItemEntity>()
            val norD = HomeNorTypeData(1, viewModelScope)
            das.add(norD)
            val wD = HomeWeatherData("地区", "温度", "风速")
            das.add(wD)
//            das.add(HomeNorTypeData("功能1"))
//            das.add(HomeNorTypeData("功能2"))
//            das.add(HomeNorTypeData("功能3"))
//            das.add(HomeNorTypeData("功能4"))
            load(das)
        }
    }


    override fun onitemDecoration(): RecyclerView.ItemDecoration? {
        return NormaltemDecoration(AppNormalTool.dip2px( 5F))
    }

}