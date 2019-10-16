package com.caesar.pandora.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.caesar.friend.fragment.FriendFragment
import com.caesar.pandora.R
import com.caesar.pandora.homepage.fragment.HomeFragment
import com.caesar.user.fragment.MineFragment
import com.caesarlib.customview.AwesomeFontTextView
import com.caesarlib.fram.view.BaseSimpleActivity
import com.google.android.material.tabs.TabLayout
import com.gyf.immersionbar.ktx.immersionBar

@Route(path = "/main/main")
class MainActivity : BaseSimpleActivity() {
    private lateinit var tabView: TabLayout
    private lateinit var vpView: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabView = findViewById(R.id.res_tools_view1)
        vpView = findViewById(R.id.res_tools_view2)
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
        val tabs = resources.getStringArray(R.array.tabs)
        val tabFonts = resources.getStringArray(R.array.tabs_font)
        for (num in 0..2) {
            val tabChild = tabView.newTab()
            val viewChild =
                LayoutInflater.from(this).inflate(R.layout.layout_main_tab, tabView, false)
            viewChild.findViewById<AwesomeFontTextView>(R.id.res_tools_tab1).text  = tabFonts[num]
            viewChild.findViewById<AppCompatTextView>(R.id.res_tools_tab2).text = tabs[num]
            tabChild.customView = viewChild
            tabView.addTab(tabChild)
        }

    }
}
