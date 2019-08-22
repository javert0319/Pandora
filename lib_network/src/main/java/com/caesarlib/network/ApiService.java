package com.caesarlib.network;

import com.caesarlib.network.bean.base.BaseYesApiBean;
import com.caesarlib.network.bean.yesapi.*;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

import java.util.Map;

/**
 * created by Caesar on 2019/3/27
 * email : 15757855271@163.com
 */
public interface ApiService {
//    @GET("?service=App.User.Login")
//    Flowable<String> getUser(@Query("sign") String sign, @Query("app_key") String app_key, @Query("username") String username, @Query("password") String password);
//
//    @FormUrlEncoded
//    @POST("?service=App.User.Login")
//    Flowable<UserRegisterData> ongetUserp(@Field("sign") String sign, @Field("app_key") String app_key, @Field("username") String username, @Field("password") String password,@Field("password1") String password1);


    @Multipart
    @POST("?service="+YesApiServiceName.REGISTER)
    Observable<BaseYesApiBean<UserRegisterData>> userRegister(@PartMap Map<String, RequestBody> options);

    @Multipart
    @POST("?service="+YesApiServiceName.LOGIN)
    Observable<BaseYesApiBean<UserLoginData>> userLogin(@PartMap Map<String, RequestBody> options);

    @Multipart
    @POST("?service="+YesApiServiceName.CAPTCREATE)
    Observable<BaseYesApiBean<CaptchaData>> CaptchaCreate(@PartMap Map<String, RequestBody> options);

    @Multipart
    @POST("?service="+YesApiServiceName.CAPTVERIFY)
    Observable<BaseYesApiBean<UserLoginData>> CaptchaVerify(@PartMap Map<String, RequestBody> options);

    @Multipart
    @POST("?service="+YesApiServiceName.PROFILE)
    Observable<BaseYesApiBean<InfoData>> userInfo(@PartMap Map<String, RequestBody> options);

    @Multipart
    @POST("?service="+YesApiServiceName.UPDATAEXTINFO)
    Observable<BaseYesApiBean<UserInfoData>> modifyUserInfo(@PartMap Map<String, RequestBody> options);

    @Multipart
    @POST("?service="+YesApiServiceName.UPLOADIMG)
    Observable<BaseYesApiBean<NormalYesData>> upLoadImg(@PartMap Map<String, RequestBody> options, @Part MultipartBody.Part file);

    @Multipart
    @POST("?service="+YesApiServiceName.DeleteFile)
    Observable<BaseYesApiBean<NormalYesData>> DeleteFile(@PartMap Map<String, RequestBody> options);
}
