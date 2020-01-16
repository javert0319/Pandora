package com.caesar.pandora.homepage.fragment.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

class HomeNorTypeData(var strData:String,var type:Int =0) : MultiItemEntity {
    override fun getItemType(): Int {
        return type
    }
}
