package com.caesarlib.res_tools

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.regex.Pattern
import kotlin.experimental.and

/**
 * describe:数据处理工具类
 * author: jihan
 * date: 2017/1/20.
 */

object CaesarStringDealTool {
    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 结果
     */
    fun StringIsNull(str: String?): Boolean {
        return str == null || str.isEmpty()
    }

    //判断字符串不为空
    fun StringIsNotNull(str: String?): Boolean {
        return str != null && str.isNotEmpty()
    }
    /**
     * 判断所有字符串是否有空
     *
     * @param strs 字符串
     * @return 结果
     */
    fun StrsIsContainNull(vararg strs: String?): Boolean {
        var result = false
        for (str in strs) {
            if (StringIsNull(str)) {
                result = true
                break
            }
        }
        return result
    }

    /**
     * 获取字符串的长度
     *
     * @param str 字符串
     * @return 结果
     */
    fun getStringLength(str: String): Int {
        return if (StringIsNull(str)) 0 else str.length
    }

    /**
     * 将字符串转换成int类型
     *
     * @param str 目标
     * @return 数字
     */
    fun StrToInt(str: String): Int {
        return if (StringIsNull(str)) {
            0
        } else Integer.valueOf(str)
    }

    /**
     * 判断两字符串是否相等
     *
     * @param s1 内容1
     * @param s2 内容2
     * @return 是否相等
     */
    fun isEqual(s1: String, s2: String): Boolean {
        if (StringIsNull(s1) && StringIsNull(s2)) {
            return true
        }
        if (StringIsNull(s1) || StringIsNull(s2)) {
            return false
        }
        return if (s1 == s2) {
            true
        } else false
    }

    /**
     * 验证手机号
     *
     * @param str 手机号
     * @return 是否是正确的手机号
     */
    fun isPhoneNumberLegal(str: String): Boolean {
        if (StringIsNull(str)) {
            return true
        }
        val regexp = "^1[34578]\\d{9}$"
        //        String regexp = "0?(13|14|15|18)[0-9]{9}";
        val pattern = Pattern.compile(regexp)
        val matcher = pattern.matcher(str)
        return matcher.matches()
    }

    /**
     * 判断字符串是否以部分字符串开始
     *
     * @param str  目标
     * @param part 部分
     * @return 是否
     */
    fun isStartWith(str: String, part: String): Boolean {
        return str.startsWith(part)
    }

    /**
     * 判断字符串是否以部分字符串结束
     *
     * @param str  目标
     * @param part 部分
     * @return 是否
     */
    fun isEndWith(str: String, part: String): Boolean {
        return str.endsWith(part)
    }

    /**
     * 删除字符串最后一个字符
     *
     * @param str 目标
     * @return 目标
     */
    fun DeleteEndChar(str: String): String {
        return str.substring(0, str.length - 1)
    }

    /**
     * 字符串MD5加密
     *
     * @param s 目标
     * @return 结果
     */
    fun MD5(s: String?): String {
        try {
            //获取md5加密对象
            val instance: MessageDigest = MessageDigest.getInstance("MD5")
            //对字符串加密，返回字节数组
            val digest: ByteArray = instance.digest(s?.toByteArray())
            var sb: StringBuffer = StringBuffer()
            for (b in digest) {
                //获取低八位有效值
                var i: Int = b.toInt() and 0xff
                //将整数转化为16进制
                var hexString = Integer.toHexString(i)
                if (hexString.length < 2) {
                    //如果是一位的话，补0
                    hexString = "0" + hexString
                }
                sb.append(hexString)
            }
            return sb.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }

    /**
     * 验证用户名是否正确
     *
     * @param str 目标
     * @return 结果
     */
    fun isUserNameLegal(str: String): Boolean {
        if (StringIsNull(str) || getStringLength(str)!! < 6 || getStringLength(str)!! > 22) {
            return false
        }
        val regexp = "^[A-Za-z0-9]+$"
        val pattern = Pattern.compile(regexp)
        val matcher = pattern.matcher(str)
        return matcher.matches()
    }

    /**
     * 验证密码是否正确
     *
     * @param str 目标
     * @return 结果
     */
    fun isPassWordLegal(str: String): Boolean {
        if (StringIsNull(str) /*|| getStringLength(str) < 8 || getStringLength(str) > 33*/) {
            return true
        }
        val regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z\\.\\_\\-]{8,20}$"
        //        String regexp = "^[.A-Za-z0-9_-]*$";
        val pattern = Pattern.compile(regexp)
        val matcher = pattern.matcher(str)
        return matcher.matches()
    }

    /**
     * 验证邮箱是否正确
     *
     * @param str 目标
     * @return 结果
     */
    fun isEmailLegal(str: String): Boolean {
        if (StringIsNull(str) /*|| getStringLength(str) < 8 || getStringLength(str) > 33*/) {
            return true
        }
        val regexp = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$"
        //        String regexp = "^[.A-Za-z0-9_-]*$";
        val pattern = Pattern.compile(regexp)
        val matcher = pattern.matcher(str)
        return matcher.matches()
    }

    /**
     * 验证微信是否正确
     *
     * @param str 目标
     * @return 结果
     */
    fun isWeiXinLegal(str: String): Boolean {
        if (StringIsNull(str) /*|| getStringLength(str) < 8 || getStringLength(str) > 33*/) {
            return true
        }
        val regexp = "^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}+$"
        //        String regexp = "^[.A-Za-z0-9_-]*$";
        val pattern = Pattern.compile(regexp)
        val matcher = pattern.matcher(str)
        return matcher.matches()
    }

    /**
     * 是否是真实姓名
     *
     * @param str 目标
     * @return 是否
     */
    fun isRealNameLegal(str: String): Boolean {
        if (StringIsNull(str)) {
            return true
        } else {
            if (str.length < 2) {
                return false
            } else {
                val regexp = "^([\\u4e00-\\u9fa5]+|([a-zA-Z]+\\s?)+)$"
                val pattern = Pattern.compile(regexp)
                val matcher = pattern.matcher(str)
                return matcher.matches()
            }
        }
    }

    /**
     * 昵称是否正规
     *
     * @param str 目标
     * @return 是否
     */
    fun isNickNameRegle(str: String): Boolean {
        return if (StringIsNull(str)) {
            false
        } else {
            if (str.length < 2 || str.length > 20) {
                false
            } else {
                true
            }
        }
    }

    /**
     * 时间字符串转换成long类型
     *
     * @param str    转换的字符串形式时间
     * @param format 时间的格式
     * @return long类型的时间
     */
    fun stringDateToLong(str: String, format: String): Long {
        if (StringIsNull(str)) {
            return 0
        }
        val sdf = SimpleDateFormat(format)
        try {
            val date = sdf.parse(str)
            return date!!.time / 1000
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return -1
    }

    /**
     * 字符串中是否包含指定字符(特定字符串,以,分隔)
     *
     * @param s        指定字符
     * @param art_type 检测字符串
     * @return 是否
     */
    fun isContain(s: String, art_type: String): Boolean {
        if (!StringIsNull(art_type)) {
            val arrstr = art_type.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (g in arrstr) {
                if (isEqual(s, g)) {
                    return true
                }
            }
            return false
        } else {
            return false
        }
    }

    /**
     * 将字符串按指定字符分割成数组返回
     *
     * @param s    内容
     * @param type 指定字符
     * @return 数据
     */
    fun onSplit(s: String, type: String): Array<String> {
        var s = s
        if (StringIsNull(s)) {
            s = ""
        }
        return s.split(type.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    }


    /**
     * 时间格式化返回字符串
     *
     * @param format  格式化形式
     * @param context 内容
     * @return 字符串
     */
    fun DataFormateStr(format: String, context: String): String {
        val sdf = SimpleDateFormat(format)
        var StrData = ""
        try {
            val f = sdf.parse(context)
            StrData = sdf.format(f!!)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return StrData
    }

    /**
     * 去除字符串转义字符
     *
     * @param str 目标
     * @return 返回
     */
    fun StrunScape(str: String?): String? {
        return str?.replace("\\", "")
    }

}
