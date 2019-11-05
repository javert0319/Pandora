package com.caesar.pandora.homepage.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caesar.pandora.R
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
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initToorBar(view,"首页")
        return view
    }


}
