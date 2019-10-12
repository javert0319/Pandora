package com.caesar.user

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.caesarlib.fram.view.BaseSimpleActivity

@Route(path = "/user/test")
class DebugTestActivity : BaseSimpleActivity() {
    override fun onFirstResume() {
    }

    private val jh = "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"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_activity_debug_test)
        initToorBar("测试")
        findViewById<Button>(R.id.user_button).setOnClickListener {
//            CSLog.I(FramGroble.getTopActivity()?.componentName?.className)
            shgd()
        }
    }

    fun shgd(){
        val df= android.util.Base64.decode(jh, android.util.Base64.DEFAULT);
        val dff = BitmapFactory.decodeByteArray(df, 0, df.size)
        findViewById<ImageView>(R.id.iv_show).setImageBitmap(dff)
    }

}
