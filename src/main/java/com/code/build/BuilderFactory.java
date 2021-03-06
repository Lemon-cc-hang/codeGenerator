package com.code.build;

import com.code.utils.TemplateUtil;
import freemarker.template.Template;

import java.io.File;
import java.util.Map;

/**
 * 建造工厂
 * @author lemoncc
 */
public class BuilderFactory {
    /**
     * 建造工厂
     * @param dataModel    数据模型
     * @param templatePath 模板路径
     * @param templateFile 模板文件
     * @param storePath    存储路径
     * @param suffix       生成文件后缀名
     */
    public static void builder(Map<String, Object> dataModel,
                               String templatePath,
                               String templateFile,
                               String storePath,
                               String suffix) {
        try {
            // 获取模板对象
            Template template = TemplateUtil.loadTemplate(BuilderFactory.class.getResource(templatePath).getPath(), templateFile);

            // 创建文件夹
            String path = TemplateBuilder.PROJECT_PATH + storePath.replace(".", "/");
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }

            // 创建文件
            TemplateUtil.writer(template, dataModel, path + "/" + dataModel.get("tableUpper") + suffix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
