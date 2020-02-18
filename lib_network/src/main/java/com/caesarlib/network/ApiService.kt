package com.caesarlib.network

import com.caesarlib.network.bean.base.BaseYesApiBean
import com.caesarlib.network.bean.yesapi.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

/**
 * created by Caesar on 2019/3/27
 * email : 15757855271@163.com
 */
interface ApiService {
    //    @GET("?service=App.User.Login")
    //    Flowable<String> getUser(@Query("sign") String sign, @Query("app_key") String app_key, @Query("username") String username, @Query("password") String password);
    //
    //    @FormUrlEncoded
    //    @POST("?service=App.User.Login")
    //    Flowable<UserRegisterData> ongetUserp(@Field("sign") String sign, @Field("app_key") String app_key, @Field("username") String username, @Field("password") String password,@Field("password1") String password1);


    @Multipart
    @POST("?service=" + YesApiServiceName.REGISTER)
    suspend fun userRegister(@PartMap options: HashMap<String, RequestBody>): BaseYesApiBean<UserRegisterData>

    @Multipart
    @POST("?service=" + YesApiServiceName.LOGIN)
    suspend fun userLogin(@PartMap options: HashMap<String, RequestBody>): BaseYesApiBean<UserLoginData>

    @Multipart
    @POST("?service=" + YesApiServiceName.CAPTCREATE)
    suspend fun CaptchaCreate(@PartMap options: HashMap<String, RequestBody>): BaseYesApiBean<CaptchaData>

    @Multipart
    @POST("?service=" + YesApiServiceName.CAPTVERIFY)
    suspend fun CaptchaVerify(@PartMap options: HashMap<String, RequestBody>): BaseYesApiBean<UserLoginData>

    @Multipart
    @POST("?service=" + YesApiServiceName.PROFILE)
    suspend fun userInfo(@PartMap options: HashMap<String, RequestBody>): BaseYesApiBean<InfoData>

    @Multipart
    @POST("?service=" + YesApiServiceName.UPDATAEXTINFO)
    suspend fun modifyUserInfo(@PartMap options: HashMap<String, RequestBody>): BaseYesApiBean<UserInfoData>

    @Multipart
    @POST("?service=" + YesApiServiceName.UPLOADIMG)
    suspend fun upLoadImg(@PartMap options: HashMap<String, RequestBody>, @Part file: MultipartBody.Part): BaseYesApiBean<NormalYesData>

    @Multipart
    @POST("?service=" + YesApiServiceName.DeleteFile)
    suspend fun DeleteFile(@PartMap options: HashMap<String, RequestBody>): BaseYesApiBean<NormalYesData>

    @Multipart
    @POST("?service=" + YesApiServiceName.OKIJOKE)
    suspend fun OkiJoke(@PartMap options: HashMap<String, RequestBody>): BaseYesApiBean<JokeData>

    @Multipart
    @POST("?service=" + YesApiServiceName.LiveWeather)
    suspend fun LiveWeather(@PartMap options: HashMap<String, RequestBody>): BaseYesApiBean<WeatherData>


}
