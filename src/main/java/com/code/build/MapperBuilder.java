package com.code.build;

import com.code.utils.TemplateUtil;
import freemarker.template.Template;

import java.io.File;
import java.util.Map;

/**
 * mapper层构造器
 * @author lemoncc
 */
public class MapperBuilder {

    /**
     * 构建mapper
     * @param dataModel 数据模型
     */
    public static void builder(Map<String,Object> dataModel){
        //生成mapper层文件
        BuilderFactory.builder(dataModel,
                "/template/mapper",
                "Mapper.java",
                TemplateBuilder.PACKAGE_MAPPER,
                TemplateBuilder.PACKAGE_MAPPER_SUFFIX + ".java");
    }

    /**
     * 创建Mapper.xml文件
     * @param dataModel 数据模型
     */
    public static void mkdirMapperXml(Map<String, Object> dataModel) {
        mkdirMapperXml(dataModel,
                "/template/mapper",
                "Mapper.xml",
                "Mapper.xml");
    }

    /**
     * 创建Mapper.xml文件
     * @param dataModel 数据模型
     * @param templatePath 模板路径
     * @param templateFile 模板文件
     * @param suffix       存储文件后缀
     */
    public static void mkdirMapperXml(Map<String, Object> dataModel,
                                      String templatePath,
                                      String templateFile,
                                      String suffix) {
        String storePath = "mapper";
        try {
            // 获取模板对象
            Template template = TemplateUtil.loadTemplate(BuilderFactory.class.getResource(templatePath).getPath(), templateFile);

            // 创建文件夹
            String path = TemplateBuilder.RESOURCES_PATH + storePath;
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
