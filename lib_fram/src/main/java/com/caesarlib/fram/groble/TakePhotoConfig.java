package com.caesarlib.fram.groble;

import android.net.Uri;
import com.caesarlib.res_tools.NormalStaticData;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TakePhotoOptions;

import java.io.File;

/**
 * describe:图片选择控件的配置
 * author：jihan
 * date:2017/11/20.
 */

public class TakePhotoConfig {


    /**
     * 图片多选,并且裁剪
     *
     * @param takePhoto 控件对象
     * @param limit     数量
     */
    public static void takePhotoACrop(TakePhoto takePhoto, int limit) {
        takePhotoSet(takePhoto);
        takePhoto.onPickMultipleWithCrop(limit, getCropOptions());
    }

    /**
     * 从相册中选择图片,不裁剪
     *
     * @param takePhoto 控件对象
     */
    public static void takePhotoByAlbum(TakePhoto takePhoto) {
        takePhotoSet(takePhoto);
        takePhoto.onPickFromGallery();
    }

    /**
     * 从相册中选择图片,并且裁剪
     *
     * @param takePhoto 控件对象
     */
    public static void takePhotoByAlbumACrop(TakePhoto takePhoto) {
        takePhotoSet(takePhoto);
        takePhoto.onPickFromGalleryWithCrop(getPhotoUri(), getCropOptions());
    }

    /**
     * 从相册中选择图片,并且裁剪
     *
     * @param takePhoto 控件对象
     * @param width     裁剪比例宽
     * @param height    裁剪比例高
     */
    public static void takePhotoByAlbumACrop(TakePhoto takePhoto, int width, int height) {
        takePhotoSet(takePhoto);
        takePhoto.onPickFromGalleryWithCrop(getPhotoUri(), getCropOptions(width, height));
    }

    /**
     * 从文件中选择图片,不裁剪
     *
     * @param takePhoto 控件对象
     */
    public static void takePhotoByFiles(TakePhoto takePhoto) {
        takePhotoSet(takePhoto);
        takePhoto.onPickFromDocuments();
    }

    /**
     * 从文件中选择图片,并且裁剪
     *
     * @param takePhoto 控件对象
     */
    public static void takePhotoByFilesACrop(TakePhoto takePhoto) {
        takePhotoSet(takePhoto);
        takePhoto.onPickFromDocumentsWithCrop(getPhotoUri(), getCropOptions());
    }

    /**
     * 从文件中选择图片,并且裁剪
     *
     * @param takePhoto 控件对象
     * @param width     裁剪比例宽
     * @param height    裁剪比例高
     */
    public static void takePhotoByFilesACrop(TakePhoto takePhoto, int width, int height) {
        takePhotoSet(takePhoto);
        takePhoto.onPickFromDocumentsWithCrop(getPhotoUri(), getCropOptions(width, height));
    }


    /**
     * 拍照,不裁剪
     *
     * @param takePhoto 控件对象
     */
    public static void takePhotoByCamera(TakePhoto takePhoto) {
        takePhotoSet(takePhoto);
        takePhoto.onPickFromCapture(getPhotoUri());
    }

    /**
     * 拍照,并且裁剪
     *
     * @param takePhoto 控件对象
     */
    public static void takePhotoByCameraACrop(TakePhoto takePhoto) {
        takePhotoSet(takePhoto);
        takePhoto.onPickFromCaptureWithCrop(getPhotoUri(), getCropOptions());
    }

    /**
     * 拍照,并且裁剪
     *
     * @param takePhoto 控件对象
     * @param width     裁剪比例宽
     * @param height    裁剪比例高
     */
    public static void takePhotoByCameraACrop(TakePhoto takePhoto, int width, int height) {
        takePhotoSet(takePhoto);
        takePhoto.onPickFromCaptureWithCrop(getPhotoUri(), getCropOptions(width, height));
    }


    /**
     * 获取拍照裁剪的地址uri
     *
     * @return
     */
    private static Uri getPhotoUri() {
        File file = new File(NormalStaticData.SDCardCacheSavePath, System.currentTimeMillis
                () + ".jpg");
        return Uri.fromFile(file);
    }


    /**
     * 对象设置，调用之前都要设置，可以自定义属性
     *
     * @param takePhoto 对象
     */
    private static void takePhotoSet(TakePhoto takePhoto) {
        setcompression(takePhoto);
        setTakePhotoOption(takePhoto);
    }

    /**
     * 压缩设置
     *
     * @param takePhoto 控件对象
     */
    private static void setcompression(TakePhoto takePhoto) {
        LubanOptions option = new LubanOptions.Builder()
                .setMaxSize(1024 * 1024 )
                .create();
        CompressConfig config = CompressConfig.ofLuban(option);
        config.enableReserveRaw(true);
        takePhoto.onEnableCompress(config, true);
    }

    /**
     * takephoto设置
     *
     * @param takePhoto 控件对象
     */
    private static void setTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true);
        takePhoto.setTakePhotoOptions(builder.create());
    }

    /**
     * 获取裁剪配置
     *
     * @return 裁剪配置
     */
    private static CropOptions getCropOptions() {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setOutputX(1080).setOutputY(1920);
        builder.setWithOwnCrop(true);
        return builder.create();
    }

    /**
     * 获取裁剪配置
     *
     * @param width  裁剪比例宽
     * @param height 裁剪比例高
     * @return 裁剪配置
     */
    private static CropOptions getCropOptions(int width, int height) {
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(width).setAspectY(height);
        builder.setWithOwnCrop(true);
        return builder.create();
    }
}
