package com.code.swagger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 方法
 * @author lemoncc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwaggerMethod {
    /**
     * 提交路径
     */
    private String url;

    /**
     * 提交方式 GET, POST, PUT, DELETE...
     */
    private String method;

    /**
     * 所属Tags
     */
    private String tags;

    /**
     * 简介
     */
    private String summary;

    /**
     * 详细描述
     */
    private String description;

    /**
     * 操作Id
     */
    private String operationId;

    /**
     * 接收数据类型
     */
    private String consumes;

    /**
     * 响应类型
     */
    private String produces;

    /**
     * 响应数据
     */
    private List<SwaggerResponse> responses;

    /**
     * 参数设置
     */
    private List<SwaggerParameter> swaggerParameters;
}
