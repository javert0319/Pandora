package com.caesarlib.network

import android.os.Build
import android.util.ArrayMap
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import java.util.HashMap
import java.util.concurrent.TimeUnit

/**
 * created by Caesar on 2019/3/27
 * email : 15757855271@163.com
 */
internal class RetrofitUtil private constructor() {
    private var retrofitMap: MutableMap<String, Retrofit>? = null
    private var okHttpClient: OkHttpClient? = null

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            retrofitMap = ArrayMap()
        } else {
            retrofitMap = HashMap()
        }
    }


    private object RetrofitUtilInstance {
        internal val INSTANCE = RetrofitUtil()

    }


    fun createRetrofit(baseUrl: String): Retrofit? {
        var retrofit = retrofitMap!![baseUrl]
        if (retrofit == null) {
            val client = createOkhttp()
            retrofit = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

            retrofitMap!![baseUrl] = retrofit
        }
        return retrofit
    }

    private fun createOkhttp(): OkHttpClient? {
        if (null == okHttpClient) {
            okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                //                    .addInterceptor(new BodyInterceptor())
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build()
        }
        return okHttpClient
    }

    companion object {
        private val DEFAULT_TIMEOUT: Long = 10

        val instance: RetrofitUtil
            get() = RetrofitUtilInstance.INSTANCE
    }


}
