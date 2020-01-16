package com.caesar.pandora.homepage.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.caesar.pandora.R
import com.caesar.pandora.databinding.FragmentHomeBinding
import com.caesarlib.fram.view.BaseFragment
import com.caesarlib.fram.view.BaseView

/**
 * 首页的碎片
 */
class HomeFragment : BaseFragment<BaseView,HomeViewModel>() {
    override fun createViewModel(): HomeViewModel {
      return  HomeViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      val binding =   DataBindingUtil.inflate<FragmentHomeBinding>(inflater,R.layout.fragment_home,container,false)
        binding.vm = mViewModel
        initToorBar(binding.root,"首页")
        mViewModel?.load()
        return binding.root
    }


}
