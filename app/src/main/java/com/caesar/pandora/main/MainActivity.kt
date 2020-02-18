package com.caesar.pandora.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.caesar.friend.fragment.FriendFragment
import com.caesar.pandora.R
import com.caesar.pandora.databinding.ActivityMainBinding
import com.caesar.pandora.homepage.fragment.HomeFragment
import com.caesar.user.fragment.MineFragment
import com.caesarlib.fram.view.BaseActivity
import com.caesarlib.fram.view.BaseView
import com.google.android.material.tabs.TabLayout

@Route(path = "/main/main")
class MainActivity : BaseActivity<BaseView, MainViewModel>() {

    override fun createViewModel(): MainViewModel {
        return MainViewModel()
    }

    private lateinit var tabView: TabLayout
    private lateinit var vpView: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.vm = mViewModel
        tabView = findViewById(R.id.res_tools_view1)
        vpView = findViewById(R.id.res_tools_view3)
    }

    override fun onFirstResume() {
        val frags = ArrayList<Fragment>()
        frags.add(HomeFragment())
        frags.add(FriendFragment())
        frags.add(MineFragment())
        vpView.adapter = MainVpAdapter(supportFragmentManager, frags)
        vpView.offscreenPageLimit = 3
        tabView.setupWithViewPager(vpView)
        tabView.removeAllTabs()
        val tabs = resources.getStringArray(R.array.tabs).toList()
        val tabFonts = resources.getStringArray(R.array.tabs_font).toList()
        mViewModel?.mtabs?.set(tabs)
        mViewModel?.mtabFonts?.set(tabFonts)
    }
}
