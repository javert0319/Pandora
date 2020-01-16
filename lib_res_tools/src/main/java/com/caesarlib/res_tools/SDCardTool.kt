package com.caesarlib.res_tools

import android.os.Environment
import android.os.StatFs

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException

/**
 * describe:sd卡工具类
 * author: jihan
 * date: 2016/9/1.
 */
object SDCardTool {
    // 判断SD卡是否被挂载
    val isSDCardMounted: Boolean
        get() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

    // 获取SD卡的根目录
    val sdCardBaseDir: String
        get() = Environment.getExternalStorageDirectory().absolutePath

    // 获取SD卡的可用空间大小
    val sdCardAvailableSize: Long
        get() {
            if (isSDCardMounted) {
                val fs = StatFs(sdCardBaseDir)
                val count = fs.availableBlocks
                val size = fs.blockSize
                return (count * size / 1024 / 1024).toLong()
            }
            return 0
        }


    /**
     * 创建文件夹
     */
    fun CreateFileIfNotExist(fileName: String) {
        val HurunShanFile = File(sdCardBaseDir, fileName)
        if (!HurunShanFile.exists()) {
            HurunShanFile.mkdirs()
        }
        NormalStaticData.SDCardSavePath = HurunShanFile.path
        CreateCacheFileIfNotExist()
    }

    /**
     * 创建缓存文件夹
     */
    fun CreateCacheFileIfNotExist() {
        val cacheFile = File(NormalStaticData.SDCardSavePath, "cache")
        if (!cacheFile.exists()) {
            cacheFile.mkdirs()
        }
        NormalStaticData.SDCardCacheSavePath = cacheFile.path
        clearCacheFile(cacheFile)
    }


    /**
     * 一个文件，如果没有，就创建
     *
     * @param filePath 地址+文件名
     */
    fun createTextFile(filePath: String) {
        val file = File(NormalStaticData.SDCardSavePath, filePath)
        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    /**
     * 清除文件夹
     *
     * @param filePath 地址+文件名
     */
    fun ClearFile(filePath: String, size: Int) {
        val file = File(filePath)
        if (file.exists()) {
            val fileSize = getFileSizes(file) / 1024 / 1024
            if (fileSize > size) {
                deleteFile(file)
            }
        }
    }

    /**
     * 获得文件夹的大小
     *
     * @param f 文件
     * @return 大小
     */
    private fun getFileSizes(f: File): Long {
        var size: Long = 0
        val files = f.listFiles()
        for (i in files!!.indices) {
            if (files[i].isDirectory) {
                size = size + getFileSizes(files[i])
            } else {
                size = size + getSize(files[i])
            }
        }
        return size

    }

    /**
     * 获得单个文件的大小
     *
     * @param file 文件
     * @return 大小
     */
    private fun getSize(file: File): Long {
        var size: Long = 0
        if (file.exists()) {
            try {
                val fis = FileInputStream(file)
                size = fis.available().toLong()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return size
    }

    /**
     * 递归删除文件/文件夹
     *
     * @param file 文件
     */
    private fun deleteFile(file: File) {
        if (file.exists()) {
            if (file.isFile) {
                file.delete()
            } else if (file.isDirectory) {
                val files = file.listFiles()
                for (i in files!!.indices) {
                    deleteFile(files[i])
                }
            }
            file.delete()
        }
    }

    /**
     * 清空缓存文件夹里的数据
     *
     * @param file 文件
     */
    private fun clearCacheFile(file: File?) {
        if (file != null && file.isDirectory) {
            val files = file.listFiles()
            for (i in files!!.indices) {
                deleteFile(files[i])
            }
        }
    }

}
