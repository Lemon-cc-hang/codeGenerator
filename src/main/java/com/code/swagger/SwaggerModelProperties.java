package com.code.swagger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 生成JavaBean的属性配置
 * @author lemoncc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwaggerModelProperties {

    /**
     * 属性名字
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 格式
     */
    private String format;

    /**
     * 描述
     */
    private String description;
}
