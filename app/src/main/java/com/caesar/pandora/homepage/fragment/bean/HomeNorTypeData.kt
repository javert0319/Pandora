package com.caesar.pandora.homepage.fragment.bean

import androidx.databinding.ObservableField
import com.alibaba.android.arouter.launcher.ARouter
import com.caesarlib.fram.viewmodel.NormalNetModel
import com.caesarlib.network.ParamsFactary
import com.caesarlib.userinfo.ValueUserData
import com.chad.library.adapter.base.entity.MultiItemEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeNorTypeData(var type: Int = 0, var viewScope: CoroutineScope? = null) : NormalNetModel(),
    MultiItemEntity {
    var strData = ObservableField<String>()

    init {
        getJokeData()
    }

    override fun getItemType(): Int {
        return type
    }

    fun getJokeData() {
        viewScope?.launch(Dispatchers.IO) {
            val result = saveApiCall {
                getDefaultApiService()?.OkiJoke(
                    ParamsFactary.okiJoke(1)
                )
            }
            if (result?.isSuccess!!) {
                strData.set(result.data?.joke?.get(0)?.get(0))
            }
        }
    }

    fun goJokeList(){
        ValueUserData.nickName.set("123dfrf")
        ARouter.getInstance().build("/function/joke").navigation()
    }
}
