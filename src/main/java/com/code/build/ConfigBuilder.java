package com.code.build;

import com.code.utils.TemplateUtil;
import freemarker.template.Template;

import java.io.File;
import java.util.Map;

/**
 * 配置类建造工厂
 * @author lemoncc
 */
public class ConfigBuilder {

    /**
     * config 建造工厂
     * @param dataModel    数据模型
     */
    public static void builder(Map<String, Object> dataModel) {
        builder(dataModel, "MyBatisPlusConfig.java");
    }

    /**
     * config建造工厂
     * @param dataModel    数据模型
     * @param templateFile 模板文件名
     */
    public static void builder(Map<String, Object> dataModel, String templateFile) {
        try {
            String templatePath = "/template/config";
            // 获取模板对象
            Template template = TemplateUtil.loadTemplate(BuilderFactory.class.getResource(templatePath).getPath(), templateFile);

            // 创建文件夹
            String path = TemplateBuilder.PROJECT_PATH + TemplateBuilder.PACKAGE_CONFIG.replace(".", "/");
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }

            // 创建文件
            TemplateUtil.writer(template, dataModel, path + "/" + templateFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
