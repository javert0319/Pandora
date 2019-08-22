package com.lib.network

import com.caesarlib.network.YesApiServiceName
import com.caesarlib.network.bean.yesapi.ExtInfoData
import com.caesarlib.res_tools.CaesarLogTool
import com.caesarlib.res_tools.CaesarStringDealTool
import com.caesarlib.userinfo.ValueUserData
import okhttp3.MediaType
import okhttp3.RequestBody
import java.util.*

/**
 * created by Caesar on 2019/4/4
 * email : 15757855271@163.com
 */
object ParamsFactary {

    private val TEXT_PLAIN = "text/plain"
    private val yesApi_app_secrect = "rTXetPWsc1DloSrnxGdMQOLjOyWZplxkOByYcaOZG2i0vOWEPXpa6hvH4vCKzAne"
    private val yesApi_app_key = "B364FE9BE8CE6A134D8FBE7631564BC1"

    private fun yesApiBaseParams(apiname: String?): MutableMap<String, RequestBody> {
        val params = HashMap<String, RequestBody>()
        params["app_key"] = createRequestBody(yesApi_app_key)
        params["service"] = createRequestBody(apiname)
        return params
    }

    private fun CreateSign(vararg ars: String?): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(yesApi_app_key)
        for (str in ars) {
            stringBuilder.append(str)
        }
        stringBuilder.append(yesApi_app_secrect)
        CaesarLogTool.I("生成的sign:$stringBuilder")
        return CaesarStringDealTool.MD5(stringBuilder.toString()).toUpperCase()
    }


    /**
     * 生成请求体
     *
     * @param content 内容
     * @return 结果
     */
    private fun createRequestBody(content: String?): RequestBody {
        return RequestBody.create(MediaType.parse(TEXT_PLAIN), content)
    }


    /**
     * 通用的请求体
     *
     * @param apiname  接口名字
     * @return 结果
     */
    fun yesApiNormalParam(apiname: String?): Map<String, RequestBody> {
        val params = yesApiBaseParams(apiname)
        params["uuid"] = createRequestBody(ValueUserData.getUserUuid())
        params["token"] = createRequestBody(ValueUserData.getUserToken())
        params["sign"] = createRequestBody(CreateSign(apiname, ValueUserData.getUserToken(), ValueUserData.getUserUuid()))


        return params
    }


    /**
     * 用户登录注册通用
     *
     * @param apiname  接口名字
     * @param username 用户名
     * @param passward 密码
     * @return 结果
     */
    fun userLoginARegisterParam(apiname: String?, username: String?, passward: String?): Map<String, RequestBody> {
        val params = yesApiBaseParams(apiname)
        params["password"] = createRequestBody(passward)
        params["username"] = createRequestBody(username)
        params["sign"] = createRequestBody(CreateSign(passward, apiname, username))
        return params
    }


    /**
     * 获取验证码
     *
     * @return 结果
     */
    fun createCaptchaParam(): Map<String, RequestBody> {
        val params = yesApiBaseParams(YesApiServiceName.CAPTCREATE)
        params["return_format"] = createRequestBody("data")
        params["sign"] = createRequestBody(CreateSign("data", YesApiServiceName.CAPTCREATE))
        return params
    }

    /**
     * 核对验证码
     *
     * @param captcha_code 验证码
     * @param captcha_id   验证码唯一id
     * @return 结果
     */
    fun verifyCaptchaParam(captcha_code: String, captcha_id: String): Map<String, RequestBody> {
        val params = yesApiBaseParams(YesApiServiceName.CAPTVERIFY)
        params["captcha_code"] = createRequestBody(captcha_code)
        params["captcha_id"] = createRequestBody(captcha_id)
        params["sign"] = createRequestBody(CreateSign(captcha_code, captcha_id, YesApiServiceName.CAPTVERIFY))
        return params
    }


    /**
     * 修改用户信息
     *
     * @return 结果
     */
    fun mofidyUserInfoParam(extInfoData: ExtInfoData?): Map<String, RequestBody> {
        val params = yesApiBaseParams(YesApiServiceName.UPDATAEXTINFO)
        params["uuid"] = createRequestBody(ValueUserData.getUserUuid())
        params["token"] = createRequestBody(ValueUserData.getUserToken())
        params["ext_info"] = createRequestBody(extInfoData?.toJson())
        params["sign"] = createRequestBody(
            CreateSign(
                extInfoData?.toJson(),
                YesApiServiceName.UPDATAEXTINFO,
                ValueUserData.getUserToken(),
                ValueUserData.getUserUuid()
            )
        )
        return params
    }

    /**
     * 删除文件
     *
     * @param fileUrl  图片地址
     * @return 结果
     */
    fun DeleteFileParam(fileUrl: String): Map<String, RequestBody> {
        val params = yesApiBaseParams(YesApiServiceName.DeleteFile)
        params["url"] = createRequestBody(fileUrl)
        params["uuid"] = createRequestBody(ValueUserData.getUserUuid())
        params["token"] = createRequestBody(ValueUserData.getUserToken())
        params["sign"] =
            createRequestBody(CreateSign(YesApiServiceName.DeleteFile, ValueUserData.getUserToken(), fileUrl, ValueUserData.getUserUuid()))
        return params
    }

}
