package com.caesar.pandora.splash

import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.caesar.pandora.R
import com.caesarlib.fram.view.BaseSimpleActivity
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ktx.immersionBar
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

@Route(path = "/main/splash")
class SplashActivity : BaseSimpleActivity() {
    override fun onFirstResume() {
        start()
    }

    private lateinit var resToolsView1: AppCompatTextView
    private var disposable: Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        resToolsView1 = findViewById(R.id.res_tools_view1)
        resToolsView1.setOnClickListener {
            disposable?.dispose()
            goMain()
        }
        immersionBar {
            hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
        }
    }

    //计时开始
   private fun start() {
        disposable =
            Flowable.intervalRange(0, 5, 0, 1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .compose(this.bindToLifecycle())
                .subscribe({
                    resToolsView1.text = (4 - it).toString()
                }, {
                    goMain()
                }, {
                    goMain()
                })
    }

    //跳转到mainAct
    private fun goMain() {
//        ARouter.getInstance().build("/user/login").navigation()
        ARouter.getInstance().build("/main/main").navigation()
        finish()
    }

}
