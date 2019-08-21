package com.caesarlib.res_tools;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * describe:sd卡工具类
 * author: jihan
 * date: 2016/9/1.
 */
public class SDCardTool {
    // 判断SD卡是否被挂载
    public static boolean isSDCardMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    // 获取SD卡的根目录
    public static String getSDCardBaseDir() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    // 获取SD卡的可用空间大小
    public static long getSDCardAvailableSize() {
        if (isSDCardMounted()) {
            StatFs fs = new StatFs(getSDCardBaseDir());
            int count = fs.getAvailableBlocks();
            int size = fs.getBlockSize();
            return count * size / 1024 / 1024;
        }
        return 0;
    }


    /**
     * 创建文件夹
     */
    public static void CreateFileIfNotExist(String fileName) {
        File HurunShanFile = new File(getSDCardBaseDir(), fileName);
        if (!HurunShanFile.exists()) {
            HurunShanFile.mkdirs();
        }
        NormalStaticData.SDCardSavePath = HurunShanFile.getPath();
        CreateCacheFileIfNotExist();
    }

    /**
     * 创建缓存文件夹
     */
    public static void CreateCacheFileIfNotExist() {
        File cacheFile = new File(NormalStaticData.SDCardSavePath, "cache");
        if (!cacheFile.exists()) {
            cacheFile.mkdirs();
        }
        NormalStaticData.SDCardCacheSavePath = cacheFile.getPath();
        clearCacheFile(cacheFile);
    }


    /**
     * 一个文件，如果没有，就创建
     *
     * @param filePath 地址+文件名
     */
    public static void createTextFile(String filePath) {
        File file = new File(NormalStaticData.SDCardSavePath, filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 清除文件夹
     *
     * @param filePath 地址+文件名
     */
    public static void ClearFile(String filePath, int size) {
        File file = new File(filePath);
        if (file.exists()) {
            long fileSize = getFileSizes(file) / 1024 / 1024;
            if (fileSize > size) {
                deleteFile(file);
            }
        }
    }

    /**
     * 获得文件夹的大小
     *
     * @param f 文件
     * @return 大小
     */
    private static long getFileSizes(File f) {
        long size = 0;
        File files[] = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                size = size + getFileSizes(files[i]);
            } else {
                size = size + getSize(files[i]);
            }
        }
        return size;

    }

    /**
     * 获得单个文件的大小
     *
     * @param file 文件
     * @return 大小
     */
    private static long getSize(File file) {
        long size = 0;
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                size = fis.available();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return size;
    }

    /**
     * 递归删除文件/文件夹
     *
     * @param file 文件
     */
    private static void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        }
    }

    /**
     * 清空缓存文件夹里的数据
     *
     * @param file 文件
     */
    private static void clearCacheFile(File file) {
        if (file != null && file.isDirectory()) {
            File files[] = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFile(files[i]);
            }
        }
    }

}
