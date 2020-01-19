package com.caesar.function.entertainment

import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.caesar.function.BR
import com.caesar.function.R
import com.caesarlib.brvahbinding.CSBravhItemBinding
import com.caesarlib.fram.decoration.NormaltemDecoration
import com.caesarlib.fram.view.BaseView
import com.caesarlib.fram.viewmodel.CSBrvahLoadMoreViewModel
import com.caesarlib.network.ParamsFactary
import com.caesarlib.res_tools.AppNormalTool
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class JokeViewModel : CSBrvahLoadMoreViewModel<BaseView, String>() {
    override fun getItemBinding(): HashMap<Int, CSBravhItemBinding<Any>> {
        val map = HashMap<Int, CSBravhItemBinding<Any>>()
        map[0] = CSBravhItemBinding(BR.data, R.layout.function_layout_item_jokes)
        return map
    }

    override fun load(mPage: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = saveApiCall {
                getDefaultApiService()?.OkiJoke(
                    ParamsFactary.okiJoke(pageSize)
                )
            }
            if (result?.isSuccess!!){
                load(result.data?.joke?.get(0))
            }
        }
    }

    override fun onitemDecoration(): RecyclerView.ItemDecoration? {
        return NormaltemDecoration(AppNormalTool.dip2px(5F))
    }
}