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
     * 构建Controller
     *
     * @param modelMap     数据模型
     * @param templatePath 模板路径
     * @param templateFile 模板文件
     * @param storePath    存储路径
     * @param suffix       生成文件后缀名
     */
    public static void builder(Map<String, Object> modelMap,
                               String templatePath,
                               String templateFile,
                               String storePath,
                               String suffix) {
        try {
            // 获取模板对象
            // TODO 看是否能修改成 BuilderFactory.class
            Template template = TemplateUtil.loadTemplate(ControllerBuilder.class.getResource(templatePath).getPath(), templateFile);

            // 创建文件夹
            String path = TemplateBuilder.PROJECT_PATH + storePath.replace(".", "/");
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }

            // 创建文件
            TemplateUtil.writer(template, modelMap, path + "/" + modelMap.get("Table") + suffix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
