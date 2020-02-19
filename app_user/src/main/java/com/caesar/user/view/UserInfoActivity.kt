package com.caesar.user.view

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.caesar.user.R
import com.caesar.user.databinding.UserActivityUserInfoBinding
import com.caesar.user.viewmodel.UserInfoViewModel
import com.caesarlib.fram.global.FramGroble
import com.caesarlib.fram.view.BaseActivity
import com.caesarlib.res_tools.CSLog
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.model.InvokeParam
import com.jph.takephoto.model.TContextWrap
import com.jph.takephoto.model.TResult
import com.jph.takephoto.permission.InvokeListener
import com.jph.takephoto.permission.PermissionManager
import com.jph.takephoto.permission.PermissionManager.TPermissionType
import com.jph.takephoto.permission.TakePhotoInvocationHandler
import kotlinx.android.synthetic.main.user_activity_user_info.*
import org.koin.androidx.viewmodel.ext.android.getViewModel


@Route(path = "/user/info")
class UserInfoActivity : BaseActivity<UserInfoView, UserInfoViewModel>(), TakePhoto.TakeResultListener, InvokeListener,
    UserInfoView {
    private lateinit var binding: UserActivityUserInfoBinding

    private var takePhoto: TakePhoto? = null

    private var invokeParam: InvokeParam? = null
    override fun createViewModel(): UserInfoViewModel {
        return getViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getTakePhoto().onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.user_activity_user_info)
        binding.model = mViewModel
        mViewModel?.takePhoto = getTakePhoto()
        initToorBar(getString(R.string.res_tools_userinfo))


    }

    override fun UserInfoUpdata() {
        res_tools_view3.upDataContxt(mViewModel?.extInfo?.get()?.yesapi_nickname)
        res_tools_view4.upDataContxt(if (mViewModel?.extInfo?.get()?.yesapi_age == 0) "" else mViewModel?.extInfo?.get()?.yesapi_age.toString())
        res_tools_view5.upDataContxt(
            if (mViewModel?.extInfo?.get()?.yesapi_sex==null) "" else mViewModel?.extInfo?.get()?.yesapi_sex
        )
        res_tools_view6.upDataContxt(mViewModel?.extInfo?.get()?.province + mViewModel?.extInfo?.get()?.city + mViewModel?.extInfo?.get()?.area)
    }

    override fun onFirstResume() {
        mViewModel?.getUserInfo()
    }


    /**
     * 获取TakePhoto实例
     * @return
     */
    fun getTakePhoto(): TakePhoto {
        if (takePhoto == null) {
            takePhoto = TakePhotoInvocationHandler.of(this).bind(TakePhotoImpl(this, this)) as TakePhoto
        }
        return takePhoto as TakePhoto
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //以下代码为处理Android6.0、7.0动态权限所需
        val type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        getTakePhoto().onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getTakePhoto().onActivityResult(requestCode, resultCode, data)
    }

    override fun takeSuccess(result: TResult?) {
        CSLog.d("拍照成功", result?.toString().toString())
        mViewModel?.uploadPic(result)
    }

    override fun takeCancel() {
        CSLog.d("拍照取消")
    }

    override fun takeFail(result: TResult?, msg: String?) {
        CSLog.d("拍照失败$msg")
    }

    override fun invoke(invokeParam: InvokeParam): TPermissionType {
        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.method)
        if (TPermissionType.WAIT == type) {
            this.invokeParam = invokeParam
        }
        return type
    }

}
