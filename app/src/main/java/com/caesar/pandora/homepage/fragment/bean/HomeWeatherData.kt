package com.caesar.pandora.homepage.fragment.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

class HomeWeatherData(var areaData :String,var tempero :String,var wind :String) : MultiItemEntity {
    override fun getItemType(): Int {
        return 2
    }
}
