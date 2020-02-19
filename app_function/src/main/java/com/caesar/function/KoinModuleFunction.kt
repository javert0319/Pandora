package com.caesar.function

import com.caesar.function.entertainment.JokeViewModel
import com.caesar.function.weather.WeatherSevenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
object KoinModuleFunction {
    val functionModule = module {
        viewModel {
            JokeViewModel()
        }
        viewModel {
            WeatherSevenViewModel()
        }
    }
}