package com.caesar.user.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.caesar.user.R
import com.caesar.user.databinding.UserFragmentMineBinding
import com.caesarlib.fram.view.BaseFragment
import com.caesarlib.fram.view.BaseView
import com.caesarlib.res_tools.CSLog
import com.caesarlib.userinfo.ValueUserData

/**
 * 我的碎片
 */
class MineFragment : BaseFragment<BaseView, MineViewModel>() {
    override fun createViewModel(): MineViewModel {
        return MineViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<UserFragmentMineBinding>(inflater, R.layout.user_fragment_mine, container, false)
        binding.vm = mViewModel
        binding.userinfo = ValueUserData
        initToorBar(binding.root, "我的")
        return binding.root
    }

    override fun onFirstUserVisibleHint() {
        CSLog.d("调用的")
    }

}
