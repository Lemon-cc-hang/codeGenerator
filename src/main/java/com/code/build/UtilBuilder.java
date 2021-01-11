package com.code.build;

import java.util.Map;

/**
 * service接口构造器
 * @author lemoncc
 */
public class UtilBuilder {
    /**
     * 构建service
     * @param dataModel 数据模型
     */
    public static void builder(Map<String,Object> dataModel){
        //生成service接口文件
        BuilderUtilFactory.builder(dataModel,
                "/template/util",
                "CrosFilter.java",
                TemplateBuilder.PACKAGE_UTIL);
        //生成service接口文件
        BuilderUtilFactory.builder(dataModel,
                "/template/util",
                "GlobalException.java",
                TemplateBuilder.PACKAGE_UTIL);
        //生成service接口文件
        BuilderUtilFactory.builder(dataModel,
                "/template/util",
                "RspData.java",
                TemplateBuilder.PACKAGE_UTIL);
    }
}