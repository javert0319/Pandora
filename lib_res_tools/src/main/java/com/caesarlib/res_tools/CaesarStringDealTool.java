package com.caesarlib.res_tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * describe:数据处理工具类
 * author: jihan
 * date: 2017/1/20.
 */

public class CaesarStringDealTool {
    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return 结果
     */
    public static boolean StringIsNull(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 判断所有字符串是否有空
     *
     * @param strs 字符串
     * @return 结果
     */
    public static boolean StrsIsContainNull(String... strs) {
        boolean result = false;
        for (String str : strs) {
            if (StringIsNull(str)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * 获取字符串的长度
     *
     * @param str 字符串
     * @return 结果
     */
    public static int getStringLength(String str) {
        return StringIsNull(str) ? 0 : str.length();
    }

    /**
     * 将字符串转换成int类型
     *
     * @param str 目标
     * @return 数字
     */
    public static int StrToInt(String str) {
        if (StringIsNull(str)) {
            return 0;
        }
        return Integer.valueOf(str);
    }

    /**
     * 判断两字符串是否相等
     *
     * @param s1 内容1
     * @param s2 内容2
     * @return 是否相等
     */
    public static boolean isEqual(String s1, String s2) {
        if (StringIsNull(s1) && StringIsNull(s2)) {
            return true;
        }
        if (StringIsNull(s1) || StringIsNull(s2)) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        return false;
    }

    /**
     * 验证手机号
     *
     * @param str 手机号
     * @return 是否是正确的手机号
     */
    public static boolean isPhoneNumberLegal(String str) {
        if (StringIsNull(str)) {
            return true;
        }
        String regexp = "^1[34578]\\d{9}$";
//        String regexp = "0?(13|14|15|18)[0-9]{9}";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 判断字符串是否以部分字符串开始
     *
     * @param str  目标
     * @param part 部分
     * @return 是否
     */
    public static boolean isStartWith(String str, String part) {
        return str.startsWith(part);
    }

    /**
     * 判断字符串是否以部分字符串结束
     *
     * @param str  目标
     * @param part 部分
     * @return 是否
     */
    public static boolean isEndWith(String str, String part) {
        return str.endsWith(part);
    }

    /**
     * 删除字符串最后一个字符
     *
     * @param str 目标
     * @return 目标
     */
    public static String DeleteEndChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    /**
     * 字符串MD5加密
     *
     * @param s 目标
     * @return 结果
     */
    public static String MD5(String s) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(s.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 验证用户名是否正确
     *
     * @param str 目标
     * @return 结果
     */
    public static boolean isUserNameLegal(String str) {
        if (StringIsNull(str) || getStringLength(str) < 6 || getStringLength(str) > 22) {
            return false;
        }
        String regexp = "^[A-Za-z0-9]+$";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 验证密码是否正确
     *
     * @param str 目标
     * @return 结果
     */
    public static boolean isPassWordLegal(String str) {
        if (StringIsNull(str) /*|| getStringLength(str) < 8 || getStringLength(str) > 33*/) {
            return true;
        }
        String regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z\\.\\_\\-]{8,20}$";
//        String regexp = "^[.A-Za-z0-9_-]*$";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 验证邮箱是否正确
     *
     * @param str 目标
     * @return 结果
     */
    public static boolean isEmailLegal(String str) {
        if (StringIsNull(str) /*|| getStringLength(str) < 8 || getStringLength(str) > 33*/) {
            return true;
        }
        String regexp = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
//        String regexp = "^[.A-Za-z0-9_-]*$";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 验证微信是否正确
     *
     * @param str 目标
     * @return 结果
     */
    public static boolean isWeiXinLegal(String str) {
        if (StringIsNull(str) /*|| getStringLength(str) < 8 || getStringLength(str) > 33*/) {
            return true;
        }
        String regexp = "^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}+$";
//        String regexp = "^[.A-Za-z0-9_-]*$";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 是否是真实姓名
     *
     * @param str 目标
     * @return 是否
     */
    public static boolean isRealNameLegal(String str) {
        if (StringIsNull(str)) {
            return true;
        } else {
            if (str.length() < 2) {
                return false;
            } else {
                String regexp = "^([\\u4e00-\\u9fa5]+|([a-zA-Z]+\\s?)+)$";
                Pattern pattern = Pattern.compile(regexp);
                Matcher matcher = pattern.matcher(str);
                return matcher.matches();
            }
        }
    }

    /**
     * 昵称是否正规
     *
     * @param str 目标
     * @return 是否
     */
    public static boolean isNickNameRegle(String str) {
        if (StringIsNull(str)) {
            return false;
        } else {
            if (str.length() < 2 || str.length() > 20) {
                return false;
            } else {
                return true;
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
    public static long stringDateToLong(String str, String format) {
        if (StringIsNull(str)) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(str);
            return date.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 字符串中是否包含指定字符(特定字符串,以,分隔)
     *
     * @param s        指定字符
     * @param art_type 检测字符串
     * @return 是否
     */
    public static boolean isContain(String s, String art_type) {
        if (!StringIsNull(art_type)) {
            String[] arrstr = art_type.split(",");
            for (String g : arrstr) {
                if (isEqual(s, g)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * 将字符串按指定字符分割成数组返回
     *
     * @param s    内容
     * @param type 指定字符
     * @return 数据
     */
    public static String[] onSplit(String s, String type) {
        if (StringIsNull(s)) {
            s = "";
        }
        return s.split(type);
    }


    /**
     * 时间格式化返回字符串
     *
     * @param format  格式化形式
     * @param context 内容
     * @return 字符串
     */
    public static String DataFormateStr(String format, String context) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String StrData = "";
        try {
            Date f = sdf.parse(context);
            StrData = sdf.format(f);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return StrData;
    }

    /**
     * 去除字符串转义字符
     *
     * @param str 目标
     * @return 返回
     */
    public static String StrunScape(String str) {
        return str.replace("\\", "");
    }

}
