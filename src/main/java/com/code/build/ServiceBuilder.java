package com.code.build;

import java.util.Map;

/**
 * service接口构造器
 * @auther lemoncc
 */
public class ServiceBuilder {
    /**
     * 构建service
     * @param dataModel 数据模型
     */
    public static void builder(Map<String,Object> dataModel){
        //生成service接口文件
        BuilderFactory.builder(dataModel,
                "/template/service",
                "Service.java",
                TemplateBuilder.PACKAGE_SERVICE,
                TemplateBuilder.PACKAGE_SERVICE_SUFFIX + ".java");
    }
}
