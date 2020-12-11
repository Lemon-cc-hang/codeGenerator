package com.code.utils;

/**
 * Java类型工具类
 * @author lemoncc
 */
public class JavaTypes {

    /***
     * 根据java.sql.Types的值返回java的类型
     * @param value 值
     * @return java类型
     */
    public static String getType(int value){
        switch (value){
            case -6:
            case 5:
            case 4:
                return "java.lang.Integer";
            case -5:
                return "java.lang.Long";
            case 6:
                return "java.lang.Float";
            case 8:
                return "java.lang.Double";
            case 91:
            case 92:
            case 93:
                return "java.util.Date";
            case 16:
                return "java.lang.Boolean";
            case 1:
            case 12:
            case -1:
            default:
                return "java.lang.String";
        }
    }

    /***
     * 去掉数据类型的包
     * @param type 数据类型
     * @return String
     */
    public static String simpleName(String type){
        return type.replace("java.lang.","").replaceFirst("java.util.","");
    }

    /***
     * 去掉数据类型的包，并且首字母小写
     * @param type 数据类型
     * @return String
     */
    public static String simpleNameLowerFirst(String type){
        //去掉前缀
        type = simpleName(type);
        //将第一个字母转成小写
        return StringUtils.firstLower(type);
    }

}
