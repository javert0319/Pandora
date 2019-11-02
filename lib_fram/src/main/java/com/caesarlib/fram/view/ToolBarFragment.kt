package com.caesarlib.fram.view

import android.app.Activity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.caesarlib.fram.R
import com.caesarlib.res_tools.CSLog
import com.gyf.immersionbar.OnKeyboardListener
import com.gyf.immersionbar.ktx.immersionBar
import com.trello.rxlifecycle2.components.support.RxFragment

/**toolbar的fragment,支持沉浸式
 * created by Caesar on 2019/2/7 0007
 * email : 15757855271@163.com
 */
abstract class ToolBarFragment : RxFragment(), OnKeyboardListener{
    protected fun initToorBar(
        view:View?,
        title: String,
        displayHomeEnable: Boolean = true,
        keyboardEnable: Boolean = false,
        colorId: Int = R.color.res_tools_AppTheme_end
    ) {
        val toobar = view?.findViewById<Toolbar>(R.id.res_tools_tb_show)
        toobar?.title = title
        immersionBar {
            //immersionBar的kotlin的用法
            titleBar(toobar)
            keyboardEnable(keyboardEnable)
            setOnKeyboardListener(this@ToolBarFragment)
            navigationBarColor(colorId)
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun onBackPressed(): Boolean {
        val count = fragmentManager!!.backStackEntryCount
        if (count <= 1) {
            ActivityCompat.finishAfterTransition(activity as Activity)
        } else {
            fragmentManager!!.popBackStack()
        }
        return true
    }

    override fun onKeyboardChange(isPopup: Boolean, keyboardHeight: Int) {
        CSLog.d("键盘改变,显示与否:$isPopup///高度:$keyboardHeight")
    }

}
