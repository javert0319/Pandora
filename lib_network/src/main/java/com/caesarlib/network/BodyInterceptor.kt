package com.caesarlib.network

import android.util.Log
import okhttp3.*
import okio.Buffer
import okio.BufferedSource
import org.json.JSONException
import org.json.JSONObject

import java.io.IOException
import java.nio.charset.Charset

/**
 * created by Caesar on 2019/4/16
 * email : 15757855271@163.com
 */
class BodyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response? {
        Log.i("拦截到的内容", "数据显示了1")
        val request = chain.request()
        Log.i("拦截到的内容", "数据显示了2")
        // try the request

        Log.i("拦截到的内容", (request == null).toString() + "")
        var originalResponse: Response? = null
        try {
            originalResponse = chain.proceed(request)
            val responseBody = originalResponse!!.body()
            val source = responseBody!!.source()
            source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source.buffer()
            var charset: Charset? = UTF8
            val contentType = responseBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(UTF8)
            }
            val bodyString = buffer.clone().readString(charset!!)
            Log.i("拦截到的内容", bodyString)

            val jsonObject = JSONObject(bodyString)
            val status = jsonObject.optString("status")
        } catch (e: IOException) {
            Log.i("拦截到的内容1", e.message)
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.i("拦截到的内容2", e.message)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("拦截到的内容3", e.message)
        }

        return originalResponse
    }

    companion object {

        private val UTF8 = Charset.forName("UTF-8")
    }
}