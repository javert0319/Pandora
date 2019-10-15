package com.caesar.pandora.homepage.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.caesar.pandora.R
import com.caesarlib.fram.view.BaseSimpleFragment

/**
 * 首页的碎片
 */
class HomeFragment : BaseSimpleFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initToorBar(view,"首页")
        return view
    }


}
