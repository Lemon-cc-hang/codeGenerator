package com.code.build;

import java.util.Map;

/**
 * controller构造器
 * @author lemoncc
 */
public class FeignBuilder {

    /***
     * 构建Controller
     * @param dataModel 数据模型
     */
    public static void builder(Map<String, Object> dataModel) {
        //生成Controller层文件
        BuilderFactory.builder(dataModel,
                "/template/feign",
                "Feign.java",
                TemplateBuilder.PACKAGE_FEIGN,
                TemplateBuilder.PACKAGE_FEIGN_SUFFIX + ".java");
    }
}
