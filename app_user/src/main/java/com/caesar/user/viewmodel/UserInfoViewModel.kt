package com.caesar.user.viewmodel

import android.Manifest
import android.content.Context
import android.text.InputType
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import com.afollestad.materialdialogs.MaterialDialog
import com.caesar.user.R
import com.caesar.user.view.UserInfoView
import com.caesarlib.customview.citychose.CityChoseDialogWorker
import com.caesarlib.fram.groble.FramGroble
import com.caesarlib.fram.groble.TakePhotoConfig
import com.caesarlib.fram.viewmodel.BaseViewModel
import com.caesarlib.network.NetFacede
import com.caesarlib.network.ParamsFactary
import com.caesarlib.network.YesApiServiceName
import com.caesarlib.network.bean.yesapi.ExtInfoData
import com.caesarlib.res_tools.AppNormalTool
import com.caesarlib.res_tools.CSLog
import com.caesarlib.res_tools.CaesarStringDealTool
import com.caesarlib.res_tools.SDCardTool
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.model.TResult
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


/**
 * created by Caesar on 2019/4/8
 * email : 15757855271@163.com
 */
class UserInfoViewModel : BaseViewModel<UserInfoView>() {

    var takePhoto: TakePhoto? = null
    val extInfo = ObservableField<ExtInfoData>()
    var oldImg = ""
    fun getUserInfo() {
        NetFacede.getInstance().defaultService.userInfo(ParamsFactary.yesApiNormalParam(YesApiServiceName.PROFILE))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isSuccess) {
                    extInfo.set(it.data.info.ext_info)
                    oldImg = it.data.info.ext_info.headImg
                    mViewRef?.get()?.UserInfoUpdata()
                }
            }
    }

    fun modifyHeadImg() {
        RxPermissions(FramGroble.getTopActivity() as AppCompatActivity).request(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        ).subscribe {
            if (it) {
                SDCardTool.CreateFileIfNotExist("VOTE")
                showChoseDialog()
            } else {
                CSLog.I("权限有错误")
            }
        }
    }

    fun DeleteOldImg() {
        if (CaesarStringDealTool.StringIsNull(oldImg)) {
            modifyUserInfo()
            return
        }
        NetFacede.getInstance().defaultService.DeleteFile(ParamsFactary.DeleteFileParam(CaesarStringDealTool.StrunScape(oldImg)))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                CSLog.I("删除图片1")
            }, {
                CSLog.I("删除图片2")
                modifyUserInfo()
            }, {
                CSLog.I("删除图片3")
                modifyUserInfo()
            })
    }

    private fun showChoseDialog() {
        MaterialDialog.Builder(FramGroble.getTopActivity() as Context).title(R.string.res_tools_photo_chose)
            .items("相册", "拍照")
            .itemsCallbackSingleChoice(-1, object : MaterialDialog.ListCallbackSingleChoice {
                override fun onSelection(dialog: MaterialDialog?, itemView: View?, which: Int, text: CharSequence?): Boolean {
                    when (text) {
                        "相册" -> {
                            TakePhotoConfig.takePhotoByAlbumACrop(takePhoto, 1080, 1080)
                        }
                        "拍照" -> {
                            TakePhotoConfig.takePhotoByCameraACrop(takePhoto, 1080, 1080)
                        }
                    }
                    return true
                }
            })
            .alwaysCallSingleChoiceCallback()
            .show()
    }

    fun uploadPic(result: TResult?) {
        val file = File(result?.image?.compressPath)
        val requestBody = RequestBody.create(
            MediaType.parse("image/jpg"),
            file
        )
        val body = MultipartBody.Part.createFormData("file", file.name, requestBody)
        NetFacede.getInstance().defaultService.upLoadImg(ParamsFactary.yesApiNormalParam(YesApiServiceName.UPLOADIMG), body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isSuccess) {
                    extInfo?.get()?.headImg = it?.data?.url
                    DeleteOldImg()
                }
            }, {
                CSLog.I("出现异常" + it.message)
            }, {
                CSLog.I("结束")
            })
    }

    fun onItemClick(v: View) {
        when (v.id) {
            R.id.res_tools_view1 -> {
                modifyHeadImg()
            }
            R.id.res_tools_view3 -> {
                showInputDialog(R.string.res_tools_nickname, FramGroble.getValueString(R.string.res_tools_nickname_input), extInfo.get()?.nickName)
            }
            R.id.res_tools_view4 -> {
                showAgeChoseDialog()
            }
            R.id.res_tools_view5 -> {
                showSexChoseDialog()
            }
            R.id.res_tools_view6 -> {
                CityChoseDialogWorker.getInstance().showCityChoseDialog((FramGroble.getTopActivity() as AppCompatActivity).fragmentManager) {
                    extInfo.get()?.province = it.province
                    extInfo.get()?.city = it.city
                    extInfo.get()?.area = it.district
                    modifyUserInfo()
                }
            }
            R.id.cb -> {

            }
        }
    }

    fun modifyUserInfo() {
        NetFacede.getInstance().defaultService.modifyUserInfo(ParamsFactary.mofidyUserInfoParam(extInfo.get()))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isSuccess) {
                    extInfo.set(it.data.ext_info)
                    oldImg = it.data.ext_info.headImg
                    mViewRef?.get()?.UserInfoUpdata()
                }
            }
    }

    fun showInputDialog(titleStr: Int, hintTxt: String?, preText: String?) {
        MaterialDialog.Builder(FramGroble.getTopActivity() as Context).title(titleStr)
            .inputType(InputType.TYPE_CLASS_TEXT)
            .input(hintTxt, preText) { _, input ->
                if (CaesarStringDealTool.StringIsNull(input.toString())) {
                    AppNormalTool.showToast(hintTxt)
                } else {
                    extInfo.get()?.nickName = input.toString()
                    modifyUserInfo()
                }
            }
            .positiveText(R.string.res_tools_confirm)
            .negativeText(R.string.res_tools_cancle)
            .canceledOnTouchOutside(false)
            .show()
    }

    fun showAgeChoseDialog() {

        val age = ArrayList<String>()
        for (a in 18..70) {
            age.add(a.toString())
        }
        MaterialDialog.Builder(FramGroble.getTopActivity() as Context).title(R.string.res_tools_age)
            .items(age)
            .itemsCallbackSingleChoice(extInfo.get()?.age as Int - 18, object : MaterialDialog.ListCallbackSingleChoice {
                override fun onSelection(dialog: MaterialDialog?, itemView: View?, which: Int, text: CharSequence?): Boolean {
                    return true
                }
            })
            .alwaysCallSingleChoiceCallback()
            .positiveText(R.string.res_tools_confirm)
            .onPositive { dialog, _ ->
                extInfo.get()?.age = dialog.selectedIndex + 18
                modifyUserInfo()
            }
            .negativeText(R.string.res_tools_cancle)
            .canceledOnTouchOutside(false)
            .show()
    }


    fun showSexChoseDialog() {
        MaterialDialog.Builder(FramGroble.getTopActivity() as Context).title(R.string.res_tools_sex)
            .items(FramGroble.getValueString(R.string.res_tools_man), FramGroble.getValueString(R.string.res_tools_woman))
            .itemsCallbackSingleChoice(extInfo.get()?.sex as Int - 1, object : MaterialDialog.ListCallbackSingleChoice {
                override fun onSelection(dialog: MaterialDialog?, itemView: View?, which: Int, text: CharSequence?): Boolean {
                    return true
                }
            })
            .alwaysCallSingleChoiceCallback()
            .positiveText(R.string.res_tools_confirm)
            .onPositive { dialog, which ->
                extInfo.get()?.sex = dialog.selectedIndex + 1
                modifyUserInfo()
            }
            .negativeText(R.string.res_tools_cancle)
            .canceledOnTouchOutside(false)
            .show()
    }

}
