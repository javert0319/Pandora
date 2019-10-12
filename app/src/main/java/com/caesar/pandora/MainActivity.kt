package com.caesar.pandora

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.caesarlib.fram.view.BaseSimpleActivity
import com.google.android.material.tabs.TabLayout

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

    }
}
