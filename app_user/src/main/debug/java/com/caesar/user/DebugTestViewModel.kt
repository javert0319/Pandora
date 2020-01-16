package com.caesar.user

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.caesarlib.brvahbinding.CSBravhItemBinding
import com.caesarlib.fram.view.BaseView
import com.caesarlib.fram.viewmodel.CSBrvahViewModel
import com.caesarlib.network.ParamsFactary
import com.caesarlib.network.YesApiServiceName
import com.caesarlib.res_tools.CSLog
import com.caesarlib.res_tools.CaesarStringDealTool
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class DebugTestViewModel : CSBrvahViewModel<BaseView, TestData>() {
    override fun getItemBinding(): HashMap<Int, CSBravhItemBinding<Any>> {
        val hashMap = HashMap<Int, CSBravhItemBinding<Any>>()
        hashMap.put(0, CSBravhItemBinding(BR.data, R.layout.user_layout_item_test))
        return hashMap
    }

    override fun load() {
        addData()
    }

    var name = ObservableField<String>("dhdghdg活动开始")


    fun addData() {
        viewModelScope.launch(Dispatchers.IO){
            val das = ArrayList<TestData>()
            val result = saveApiCall {
                getDefaultApiService()?.userLogin(
                    ParamsFactary.userLoginARegisterParam(
                        YesApiServiceName.LOGIN,
                        "15757855271",
                        CaesarStringDealTool.MD5("123456")
                    )
                )
            }
            das.add(TestData(ObservableField<String>("dfhdfh")))
            das.add(TestData(ObservableField<String>("11")))
            das.add(TestData(ObservableField<String>("22")))
            das.get(9)
            load(das)
        }
    }


    fun doNetTest() {
        viewModelScope.launch(Dispatchers.IO) {
            CSLog.d("调用了"+Thread.currentThread().name)
            val result = saveApiCall {
                getDefaultApiService()?.userLogin(
                    ParamsFactary.userLoginARegisterParam(
                        YesApiServiceName.LOGIN,
                        "15757855271",
                        CaesarStringDealTool.MD5("123456")
                    )
                )
            }
            CSLog.d("调用了2"+Thread.currentThread().name)

        }

    }
}