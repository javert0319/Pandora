package com.caesarlib.network;

import android.util.Log;
import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * created by Caesar on 2019/4/16
 * email : 15757855271@163.com
 */
public class BodyInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) {
        Log.i("拦截到的内容","数据显示了1");
        Request request = chain.request();
        Log.i("拦截到的内容","数据显示了2");
        // try the request

        Log.i("拦截到的内容",  (request == null)+"");
        Response originalResponse = null;
        try {
         originalResponse = chain.proceed(request);
        ResponseBody responseBody = originalResponse.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }
        String bodyString = buffer.clone().readString(charset);
        Log.i("拦截到的内容", bodyString);

            JSONObject jsonObject = new JSONObject(bodyString);
            String status = jsonObject.optString("status");
        }catch (IOException e){
            Log.i("拦截到的内容1", e.getMessage());
        }catch (JSONException e) {
            e.printStackTrace();
            Log.i("拦截到的内容2", e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            Log.i("拦截到的内容3", e.getMessage());
        }

        return originalResponse;
    }
}