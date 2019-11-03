package com.caesar.user.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.caesar.user.R
import com.caesarlib.fram.view.BaseSimpleFragment

/**
 * 我的碎片
 */
class MineFragment : BaseSimpleFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.user_fragment_mine, container, false)
        initToorBar(view,"我的")
        return view
    }


}
