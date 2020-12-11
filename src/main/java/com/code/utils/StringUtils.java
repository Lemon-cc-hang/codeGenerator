package com.code.utils;

/**
 * 字符串处理
 * @author lemoncc
 */
public class StringUtils {

    /**
     * 首字母大写
     * @param str 原字符串
     * @return 处理后字符串
     */
    public static String firstUpper(String str) {
        if (str.length() > 1) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } else {
            return str.toUpperCase();
        }
    }

    /**
     * 首字母小写
     *
     * @param str 原字符串
     * @return 处理后字符串
     */
    public static String firstLower(String str) {
        if (str.length() > 1) {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        } else {
            return str.toLowerCase();
        }
    }

    /**
     * 移除tab_,tb_
     * @param str 原字符
     * @return 处理后字符串
     */
    public static String replaceTab(String str) {
        return str.replaceFirst("tab_", "").replaceFirst("tb_", "");
    }

    /**
     * 将下划线替换掉
     * @param str 原字符
     * @return 处理后字符串
     */
    public static String replace(String str) {
        //根据下划线分割
        String[] split = str.split("_");
        //循环组装
        StringBuilder result = new StringBuilder(split[0]);
        if (split.length > 1) {
            for (int i = 1; i < split.length; i++) {
                result.append(firstUpper(split[i]));
            }
        }
        return result.toString();
    }

    /**
     * 移除表前缀
     * @param str 原字符
     * @param prefix 前缀
     * @return 处理后字符串
     */
    public static String removePrefix(String str, String[] prefix) {
        if (null != prefix) {

            for(int i = 0; i < prefix.length; ++i) {
                String pf = prefix[i];
                if (str.toLowerCase().matches("^" + pf.toLowerCase() + ".*")) {
                    // 截取前缀后面的字符串
                    return str.substring(pf.length());
                }
            }
        }
        return str;
    }

}
