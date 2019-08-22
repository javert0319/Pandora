package com.caesar.pandora.splash

import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.caesar.pandora.R
import com.lib.fram.view.BaseSimpleActivity
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

@Route(path = "/main/splash")
class SplashActivity : BaseSimpleActivity() {
    override fun onFirstResume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var tv_timer: AppCompatTextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        tv_timer = findViewById(R.id.tv_timer)
        start()
    }

    fun start() {
        Flowable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .compose(this.bindToLifecycle())
            .subscribe({
                tv_timer?.text = (4 - it).toString()
                if ((it as Long) == 4L) {
                    ARouter.getInstance().build("/user/login").navigation()
//                    ARouter.getInstance().build("/main/main").navigation()
                }
            }, {
                ARouter.getInstance().build("/user/login").navigation()
//                ARouter.getInstance().build("/main/main").navigation()
            })
    }

}
