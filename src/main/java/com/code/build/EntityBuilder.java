package com.code.build;

import java.util.Map;

/**
 * 实体类建造器
 * @author lemoncc
 */
public class EntityBuilder {

    /**
     * 构建entity
     * @param dataModel 数据模型
     */
    public static void builder(Map<String, Object> dataModel) {
        // 生成entity层文件
        BuilderFactory.builder(dataModel,
                "/template/entity",
                "Entity.java",
                TemplateBuilder.PACKAGE_ENTITY,
                TemplateBuilder.PACKAGE_ENTITY_SUFFIX + ".java");
    }
}
