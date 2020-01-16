package com.caesarlib.res_tools

import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * describe:图片加载工具
 * author：jihan
 * date:2017/7/25.
 */

object ImageLoadTool {
    /**
     * 通用加载本地图片
     *
     * @param imageView 控件
     * @param imgRes    图片
     */
    fun onLoadLocalImg(imageView: ImageView, imgRes: Int) {
        onLoadLocalImgByGlide(imageView, imgRes)
    }

    /**
     * 通用加载本地图片
     *
     * @param imageView 控件
     * @param path      图片路径
     */
    fun onLoadLocalImg(imageView: ImageView, path: String) {
        onLoadLocalImgByGlide(imageView, path)
    }

    fun onLoadNetImgAutoHeight(imageView: ImageView, imgRes: String) {
        onLoadNetImgByGlideAutoHeight(imageView, imgRes)
    }

    /**
     * 通用加载网络图片
     *
     * @param imageView 控件
     * @param netUrl    图片路径
     */
    @JvmStatic
    @BindingAdapter(value = ["netUrl", "defaultImgRes"], requireAll = false)
    fun onLoadNetImg(imageView: ImageView, netUrl: String?, defaultImgRes: Int) {
        onLoadNetImgByGlide(imageView, netUrl, defaultImgRes)
    }

    @JvmStatic
    @BindingAdapter("base64Load")
    fun onBase64Img(imageView: ImageView?, decod64: String?) {
        if (imageView != null && CaesarStringDealTool.StringIsNotNull(decod64)) {
            val decod = Base64.decode(decod64, Base64.DEFAULT)
            Glide.with(ToolsGroble.appContext).load(decod).into(imageView)
        }
    }

    /**
     * 通用加载本地图片
     *
     * @param imageView 控件
     * @param path      图片路径
     */
    fun onLoadNetImg(imageView: ImageView, path: String) {
        onLoadNetImgByGlide(imageView, path)
    }


    /**
     * 用glide加载本地图片
     *
     * @param imageView 控件
     * @param imgRes    图片
     */
    private fun onLoadLocalImgByGlide(imageView: ImageView?, imgRes: Int) {
        if (imageView != null && imgRes != 0) {
            Glide.with(ToolsGroble.appContext).load(imgRes).into(imageView)
        }

    }

    /**
     * 用glide加载本地图片
     *
     * @param imageView 控件
     * @param imgRes    图片
     */
    private fun onLoadLocalImgByGlide(imageView: ImageView?, imgRes: String) {
        if (imageView != null) {
            Glide.with(ToolsGroble.appContext).load(imgRes).into(imageView)
        }

    }

    /**
     * 用glide加载网络图片
     *
     * @param imageView 控件
     * @param imgRes    图片
     */
    private fun onLoadNetImgByGlide(imageView: ImageView?, imgRes: String?, DefaultHead: Int) {
        if (imageView != null) {
            Glide.with(ToolsGroble.appContext).load(
                if (CaesarStringDealTool.StringIsNull(imgRes))
                    ""
                else
                    imgRes
            ).error(DefaultHead).into(imageView)
        }

    }

    /**
     * 用glide加载网络图片
     *
     * @param imageView 控件
     * @param imgRes    图片
     */
    private fun onLoadNetImgByGlide(imageView: ImageView?, imgRes: String) {
        if (imageView != null) {
            Glide.with(ToolsGroble.appContext).load(
                if (CaesarStringDealTool.StringIsNull(imgRes))
                    ""
                else
                    imgRes
            ).into(imageView)
        }

    }

    /**
     * 用glide加载网络图片
     *
     * @param imageView 控件
     * @param imgRes    图片
     */
    private fun onLoadNetImgByGlideAutoHeight(imageView: ImageView, imgRes: String) {
        //        if (imageView != null && !CaesarStringDealTool.StringIsNull(imgRes)) {
        //            Glide.with(ToolsGroble.getAppContext()).load(imgRes).asBitmap().into(new SimpleTarget<Bitmap>() {
        //
        //                @Override
        //                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
        //                    int imagewidth = resource.getWidth();
        //                    int imageHeight = resource.getHeight();
        //                    ViewGroup.LayoutParams para = imageView.getLayoutParams();
        //                    para.width = BaseApplication.screenWidth;
        //                    para.height = BaseApplication.screenWidth * imageHeight / imagewidth;
        //                    imageView.setImageBitmap(resource);
        //
        //                }
        //            });
        //        }

    }

}
