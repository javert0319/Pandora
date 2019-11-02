package com.caesar.user

import android.util.Log
import com.caesarlib.network.bean.base.BaseYesApiBean
import com.caesarlib.network.bean.yesapi.UserLoginData
import java.io.IOException

/**
 * Created by luyao
 * on 2019/4/10 9:41
 */
open class BaseRepository {


    suspend fun <T : Any> safeApiCall(call: suspend () -> BaseYesApiBean<T>): BaseYesApiBean<T> {
        return try {
            call()
        } catch (e: Exception) {
            // An exception was thrown when calling the API so we're converting this to an IOException
//            Result.Error(IOException(errorMessage, e))
            Log.d("caesar","出现异常:"+e)
            BaseYesApiBean()
        }
    }


}