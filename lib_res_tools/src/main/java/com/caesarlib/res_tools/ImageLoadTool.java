package com.caesarlib.res_tools;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;

/**
 * describe:图片加载工具
 * author：jihan
 * date:2017/7/25.
 */

public class ImageLoadTool {
    /**
     * 通用加载本地图片
     *
     * @param imageView 控件
     * @param imgRes    图片
     */
    public static void onLoadLocalImg(ImageView imageView, int imgRes) {
        onLoadLocalImgByGlide(imageView, imgRes);
    }

    /**
     * 通用加载本地图片
     *
     * @param imageView 控件
     * @param path      图片路径
     */
    public static void onLoadLocalImg(ImageView imageView, String path) {
        onLoadLocalImgByGlide(imageView, path);
    }

    public static void onLoadNetImgAutoHeight(ImageView imageView, String imgRes) {
        onLoadNetImgByGlideAutoHeight(imageView, imgRes);
    }

    /**
     * 通用加载网络图片
     *
     * @param imageView 控件
     * @param netUrl    图片路径
     */
    @BindingAdapter(value = {"netUrl", "defaultImgRes"}, requireAll = false)
    public static void onLoadNetImg(ImageView imageView, String netUrl, int defaultImgRes) {
        onLoadNetImgByGlide(imageView, netUrl, defaultImgRes);
    }

    /**
     * 通用加载本地图片
     *
     * @param imageView 控件
     * @param path      图片路径
     */
    public static void onLoadNetImg(ImageView imageView, String path) {
        onLoadNetImgByGlide(imageView, path);
    }


    /**
     * 用glide加载本地图片
     *
     * @param imageView 控件
     * @param imgRes    图片
     */
    private static void onLoadLocalImgByGlide(ImageView imageView, int imgRes) {
        if (imageView != null && imgRes != 0) {
            Glide.with(ToolsGroble.getAppContext()).load(imgRes).into(imageView);
        }

    }

    /**
     * 用glide加载本地图片
     *
     * @param imageView 控件
     * @param imgRes    图片
     */
    private static void onLoadLocalImgByGlide(ImageView imageView, String imgRes) {
        if (imageView != null) {
            Glide.with(ToolsGroble.getAppContext()).load(imgRes).into(imageView);
        }

    }

    /**
     * 用glide加载网络图片
     *
     * @param imageView 控件
     * @param imgRes    图片
     */
    private static void onLoadNetImgByGlide(ImageView imageView, String imgRes, int DefaultHead) {
        if (imageView != null) {
            Glide.with(ToolsGroble.getAppContext()).load(CaesarStringDealTool.StringIsNull(imgRes) ? ""
                    : imgRes).error(DefaultHead).into(imageView);
        }

    }

    /**
     * 用glide加载网络图片
     *
     * @param imageView 控件
     * @param imgRes    图片
     */
    private static void onLoadNetImgByGlide(ImageView imageView, String imgRes) {
        if (imageView != null) {
            Glide.with(ToolsGroble.getAppContext()).load(CaesarStringDealTool.StringIsNull(imgRes) ? ""
                    : imgRes).into(imageView);
        }

    }

    /**
     * 用glide加载网络图片
     *
     * @param imageView 控件
     * @param imgRes    图片
     */
    private static void onLoadNetImgByGlideAutoHeight(final ImageView imageView, String imgRes) {
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
