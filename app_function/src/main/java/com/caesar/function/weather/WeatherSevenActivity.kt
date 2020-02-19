package com.caesar.function.weather

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.caesar.function.R
import com.caesarlib.fram.view.BaseActivity
import com.caesarlib.fram.view.BaseView
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@Route(path = "/function/seven_weather")
class WeatherSevenActivity : BaseActivity<BaseView, WeatherSevenViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.function_activity_weather_seven)
    }

    override fun createViewModel(): WeatherSevenViewModel {
        return getViewModel()
    }

    override fun onFirstResume() {
    }
}
