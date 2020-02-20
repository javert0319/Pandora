package com.caesar.function.weather

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.caesar.function.R
import com.caesar.function.databinding.FunctionActivityWeatherSevenBinding
import com.caesarlib.fram.view.BaseActivity
import com.caesarlib.fram.view.BaseView
import org.koin.androidx.viewmodel.ext.android.getViewModel

@Route(path = "/function/seven_weather")
class WeatherSevenActivity : BaseActivity<BaseView, WeatherSevenViewModel>() {

    override fun createViewModel(): WeatherSevenViewModel {
        return getViewModel()
    }

    override fun onFirstResume() {
        mViewModel?.reloadA()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<FunctionActivityWeatherSevenBinding>(
            this,
            R.layout.function_activity_weather_seven
        )
        binding.vm = mViewModel
        mViewModel?.mRecyclerViewA = binding.rvShowTop
        initToorBar("一周天气")

    }


}
