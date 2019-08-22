package com.caesarlib.network;

import android.os.Build;
import android.util.ArrayMap;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * created by Caesar on 2019/3/27
 * email : 15757855271@163.com
 */
class RetrofitUtil {
    private Map<String, Retrofit> retrofitMap;
    private OkHttpClient okHttpClient;
    private final static long DEFAULT_TIMEOUT = 10;

    private RetrofitUtil() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            retrofitMap = new ArrayMap<>();
        } else {
            retrofitMap = new HashMap<>();
        }
    }


    private static class RetrofitUtilInstance {
        private static final RetrofitUtil INSTANCE = new RetrofitUtil();

    }

    static RetrofitUtil getInstance() {
        return RetrofitUtilInstance.INSTANCE;
    }


    Retrofit createRetrofit(String baseUrl) {
        Retrofit retrofit = retrofitMap.get(baseUrl);
        if (retrofit == null) {
            OkHttpClient client = createOkhttp();
            retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();

            retrofitMap.put(baseUrl, retrofit);
        }
        return retrofit;
    }

    private OkHttpClient createOkhttp() {
        if (null == okHttpClient) {
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                    .addInterceptor(new BodyInterceptor())
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
        }
        return okHttpClient;
    }


}
