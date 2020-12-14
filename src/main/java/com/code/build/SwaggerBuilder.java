package com.code.build;

import java.util.Map;

/**
 * @author lemoncc
 */
public class SwaggerBuilder {

    /***
     * Swagger构建
     * @param dataModel 数据模型
     */
    public static void builder(Map<String,Object> dataModel){
        //swagger的文件名字
        dataModel.put("tableUpper","swagger");

        //生成ServiceImpl层文件
        BuilderFactory.builder(dataModel,
                "/template/swagger",
                "swagger.json",
                TemplateBuilder.SWAGGER_PATH,
                ".json");
    }
}
