package com.caesar.user

import com.caesar.user.fragment.MineViewModel
import com.caesar.user.viewmodel.LoginViewModel
import com.caesar.user.viewmodel.RegisterViewModel
import com.caesar.user.viewmodel.UserInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Caesar
 * email : caesarshao@163.com
 */
object KoinModuleUser {
    val userModule = module {
        viewModel {
            LoginViewModel()
        }
        viewModel {
            RegisterViewModel()
        }
        viewModel {
            UserInfoViewModel()
        }
        viewModel {
            MineViewModel()
        }
    }
}