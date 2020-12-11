package com.code.build;

import java.util.Map;

/**
 * ServiceImpl构造器
 */
public class ServiceImplBuilder {

    /**
     * 构建serviceImpl
     * @param dataModel 数据模型
     */
    public static void builder(Map<String,Object> dataModel){
        //生成service层文件
        BuilderFactory.builder(dataModel,
                "/template/service/impl",
                "ServiceImpl.java",
                TemplateBuilder.PACKAGE_SERVICE_IMPL,
                "ServiceImpl.java");
    }
}
