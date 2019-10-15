package com.caesar.friend.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caesar.friend.R
import com.caesarlib.fram.view.BaseSimpleFragment

/**
 * 朋友的碎片
 */
class FriendFragment : BaseSimpleFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.friend_fragment_friend, container, false)
        initToorBar(view,"朋友")
        return view
    }



}
