package com.caesar.pandora

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.caesarlib.fram.view.BaseSimpleActivity

@Route(path = "/main/main")
class MainActivity : BaseSimpleActivity() {
    override fun onFirstResume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
