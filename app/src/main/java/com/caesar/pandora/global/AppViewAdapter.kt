package com.caesar.pandora.global

import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import com.caesar.pandora.R
import com.caesarlib.customview.AwesomeFontTextView
import com.caesarlib.customview.ContainRadioGroup
import com.caesarlib.fram.beans.KeyValueSData
import com.caesarlib.fram.global.FramGroble
import com.caesarlib.res_tools.CSLog
import com.google.android.material.tabs.TabLayout

object AppViewAdapter {
    @JvmStatic
    @BindingAdapter(value = ["tabsshow", "tabFontsshow"], requireAll = true)
    fun onTabViewAdapter(tabView: TabLayout?, tabs: List<String>?, tabFonts: List<String>?) {
        if (tabs != null && tabFonts != null) {
            for (num in tabs.indices) {
                val tabChild = tabView?.newTab()
                val viewChild =
                    LayoutInflater.from(tabView?.context).inflate(R.layout.layout_main_tab, tabView, false)
                viewChild.findViewById<AwesomeFontTextView>(R.id.res_tools_tab1).text = tabFonts[num]
                viewChild.findViewById<AppCompatTextView>(R.id.res_tools_tab2).text = tabs[num]
                tabChild?.customView = viewChild
                tabView?.addTab(tabChild!!)
            }
        }
    }


    @JvmStatic
    @BindingAdapter(value = ["WeatherRadioSet"])
    fun onLoadMoreEnd(containRadioGroup: ContainRadioGroup, datas: ObservableArrayList<KeyValueSData>) {
        containRadioGroup.removeAllViews()
        val infator = LayoutInflater.from(FramGroble.getApp())
        for (data in datas) {
            val viewG = infator.inflate(R.layout.item_text_type, null) as TextView
            viewG.setText(data.key +" : " +data.valueStr)
            containRadioGroup.addView(viewG)
        }
    }
}