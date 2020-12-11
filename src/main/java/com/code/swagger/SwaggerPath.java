package com.code.swagger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Swagger请求路径配置
 * @author lemoncc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwaggerPath {

    /**
     * 请求路径
     */
    private String path;

    /**
     * 方法配置
     */
    private List<SwaggerMethod> methods;

    /**
     * 对象
     */
    private String model;

    /**
     * 对象-首字母小写
     */
    private String table;

    public SwaggerPath(String model, String table) {
        this.model = model;
        this.table = table;
    }
}
