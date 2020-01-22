package com.caesar.friend.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.caesar.friend.R
import com.caesar.friend.databinding.FriendFragmentFriendBindingImpl
import com.caesarlib.fram.view.BaseSimpleFragment
import com.caesarlib.userinfo.ValueUserData

/**
 * 朋友的碎片
 */
class FriendFragment : BaseSimpleFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = inflater.inflate(R.layout.friend_fragment_friend, container, false)
      val binding =  DataBindingUtil.inflate<FriendFragmentFriendBindingImpl>(inflater,R.layout.friend_fragment_friend, container, false)
        binding.userinfo = ValueUserData
        initToorBar(binding.root,"朋友")
        return binding.root
    }



}
