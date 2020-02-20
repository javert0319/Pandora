package com.caesarlib.fram.view

import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.caesarlib.fram.R
import com.caesarlib.res_tools.CSLog
import com.gyf.immersionbar.OnKeyboardListener
import com.gyf.immersionbar.ktx.immersionBar
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * toolbar的Activity,支持沉浸式
 * created by Caesar on 2019/2/2
 * email : 15757855271@163.com
 */
abstract class ToolBarActivity : RxAppCompatActivity(), OnKeyboardListener {
    var rightTxt: TextView? = null
    protected fun initToorBar(
        title: String,
        displayHomeEnable: Boolean = true,
        keyboardEnable: Boolean = false,
        colorId: Int = R.color.res_tools_AppTheme_end
    ) {
        val toobar = findViewById<Toolbar>(R.id.res_tools_tb_show)
        toobar.title = title
        immersionBar {
            //immersionBar的kotlin的用法
            titleBar(toobar)
            keyboardEnable(keyboardEnable)
            setOnKeyboardListener(this@ToolBarActivity)
            navigationBarColor(colorId)
        }
        setSupportActionBar(toobar)
        supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeEnable)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun onKeyboardChange(isPopup: Boolean, keyboardHeight: Int) {
        CSLog.d("键盘改变,显示与否:$isPopup///高度:$keyboardHeight")
    }
}
