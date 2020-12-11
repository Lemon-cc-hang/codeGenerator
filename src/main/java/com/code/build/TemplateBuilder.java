package com.code.build;

import com.code.swagger.*;
import com.code.utils.JavaTypes;
import com.code.utils.ModelInfo;
import com.code.utils.StringUtils;

import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * 模板构造
 * @author lemoncc
 */
public class TemplateBuilder {

    /**
     * 配置文件
     */
    private static final Properties PROPERTIES = new Properties();

    /**
     * 生成的根目录
     */
    public static String PACKAGE_PARENT;

    /**
     * Entity
     */
    public static String PACKAGE_ENTITY;

    /**
     * Mapper
     */
    public static String PACKAGE_MAPPER;

    /**
     * Service 层
     */
    public static String PACKAGE_SERVICE;

    /**
     * ServiceImpl
     */
    public static String PACKAGE_SERVICE_IMPL;

    /**
     * Controller
     */
    public static String PACKAGE_CONTROLLER;

    /**
     * mysql-username
     */
    public static String MYSQL_USERNAME;

    /**
     * mysql-password
     */
    public static String MYSQL_PASSWORD;

    /**
     * mysql-driver-class-name
     */
    public static String MYSQL_DRIVER_CLASS_NAME;

    /**
     * mysql-url
     */
    public static String MYSQL_URL;

    /**
     * 项目路径
     */
    public static String PROJECT_PATH;

    /**
     * 是否使用swagger
     *
     */
    public static Boolean SWAGGER;

    /**
     * swagger-ui路径
     */
    public static String SWAGGER_UI_PATH;

    /**
     * TODO 不明白
     *  服务名字
     */
    public static String SERVICE_NAME;

    /**
     * 需要去除的前缀
     */
    public static String[] TABLE_PREFIX;

    /**
     * Mysql
     */
    public static final String MYSQL = "MySQL";

    /**
     * HashMap的默认长度
     */
    public static final int HASH_MAP_SIZE = 16;

    /**
     * 作者名字
     */
    public static String AUTHOR;

    /**
     * 需要生成的表名
     */
    public static String[] TABLE_NAME;

    /**
     * 时间
     */
    public static final String DATE = new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime());

    static {
        try {
            // 加载配置文件
            InputStream is = TemplateBuilder.class.getClassLoader().getResourceAsStream("application.properties");

            // 创建properties对象
            PROPERTIES.load(is);

            // 获取对应的配置信息
            PACKAGE_PARENT = PROPERTIES.getProperty("packageConfig.parent");
            PACKAGE_ENTITY = PACKAGE_PARENT + "." + PROPERTIES.getProperty("packageConfig.entity");
            PACKAGE_MAPPER = PACKAGE_PARENT + "." + PROPERTIES.getProperty("packageConfig.mapper");
            PACKAGE_SERVICE = PACKAGE_PARENT + "." + PROPERTIES.getProperty("packageConfig.service");
            PACKAGE_SERVICE_IMPL = PACKAGE_PARENT + "." + PROPERTIES.getProperty("packageConfig.serviceImpl");
            PACKAGE_CONTROLLER = PACKAGE_PARENT + "." + PROPERTIES.getProperty("packageConfig.controller");
            MYSQL_USERNAME = PROPERTIES.getProperty("mysql.datasource.username");
            MYSQL_PASSWORD = PROPERTIES.getProperty("mysql.datasource.password");
            MYSQL_URL = PROPERTIES.getProperty("mysql.datasource.url");
            MYSQL_DRIVER_CLASS_NAME = PROPERTIES.getProperty("mysql.datasource.driver-class-name");
            SWAGGER = Boolean.valueOf(PROPERTIES.getProperty("swagger.enable"));
            SERVICE_NAME = PROPERTIES.getProperty("service.name");
            SWAGGER_UI_PATH = PACKAGE_PARENT + "." + PROPERTIES.getProperty("swagger.ui.path");
            TABLE_PREFIX = PROPERTIES.getProperty("table.prefix").split(",");
            AUTHOR = PROPERTIES.getProperty("author");
            TABLE_NAME = PROPERTIES.getProperty("table.name").split(",");

            // 工程路径
            PROJECT_PATH = Objects.requireNonNull(TemplateBuilder.class.getClassLoader().getResource("")).getPath().replace("/target/classes", "") + "/src/main/java/";

            // 加载数据库驱动
            Class.forName(MYSQL_DRIVER_CLASS_NAME);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 模板构造
     */
    public static void builder() {
        try {
            // 获取数据库连接
            Connection connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
            DatabaseMetaData metaData = connection.getMetaData();
            
            // 获取数据库类型: MYSQL
            String databaseType = metaData.getDatabaseProductName();
            
            // 针对MySQL数据库进行相关的生成操作
            if (databaseType.equals(MYSQL)) {

                // 获取数据库名字
                String database = connection.getCatalog();

                // Swagger信息集合
                List<SwaggerModel> swaggerModels = new ArrayList<>();
                List<SwaggerPath> swaggerPaths = new ArrayList<>();

                // 获取所有表结构
                for (String table_ : TABLE_NAME) {
                    ResultSet tables = metaData.getTables(null, "%", table_, new String[]{"TABLE"});
                    // 循环所有表信息
                    while (tables.next()) {
                        // 获取表名
                        String tableName = tables.getString("TABLE_NAME");
                        // 名字操作, 去表tab_, tb_, 去掉_并转驼峰
                        String table = StringUtils.replace(StringUtils.replaceTab(StringUtils.removePrefix(tableName, TABLE_PREFIX)));
                        // 大写对象
                        String tableUpper = StringUtils.firstUpper(table);

                        // 需要生成的Pojo属性集合
                        List<ModelInfo> models = new ArrayList<>();
                        // 所有需要导包的类型
                        Set<String> typeSet = new HashSet<>();

                        // 获取表所有的列
                        ResultSet columnsSet = metaData.getColumns(database, MYSQL_USERNAME, tableName, null);
                        // 获取所有的主键
                        ResultSet keySet = metaData.getPrimaryKeys(database, MYSQL_USERNAME, tableName);
                        String key = "";
                        while (keySet.next()) {
                            key = keySet.getString(4);
                        }

                        // 构建SwaggerModel对象
                        SwaggerModel swaggerModel = new SwaggerModel();
                        swaggerModel.setName(tableUpper);
                        swaggerModel.setType("object");
                        swaggerModel.setDescription(tableUpper);

                        Map<String, Object> tempMap = getSwaggerModelPropertiesAndKeyType(columnsSet, models, key, typeSet);
                        assert tempMap != null;
                        Object swaggerModelProperties = tempMap.get("swaggerModelProperties");
                        String keyType = (String) tempMap.get("keyType");

                        // 属性集合
                        swaggerModel.setProperties((List<SwaggerModelProperties>) swaggerModelProperties);
                        swaggerModels.add(swaggerModel);

                        // 创建该表的JavaBean
                        Map<String, Object> modelMap = new HashMap<>(HASH_MAP_SIZE);
                        modelMap.put("author", AUTHOR);
                        modelMap.put("table", table);
                        modelMap.put("tableUpper", tableUpper);
                        modelMap.put("swagger", SWAGGER);
                        modelMap.put("tableName", tableName);
                        modelMap.put("models", models);
                        modelMap.put("typeSet", typeSet);
                        modelMap.put("date", DATE);
                        // 主键操作
                        modelMap.put("keySetMethod", "set" + StringUtils.firstUpper(StringUtils.replace(key)));
                        modelMap.put("keyType", keyType);
                        // TODO SERVICE_NAME
                        modelMap.put("serviceName", SERVICE_NAME);

                        // TODO 创建JavaBean
                        EntityBuilder.builder(modelMap);

                        // 添加swagger路径映射
                        String format = "string";
                        if ("integer".equalsIgnoreCase(keyType) || "long".equalsIgnoreCase(keyType)) {
                            format = "int64";
                        }
                        swaggerPaths.addAll(swaggerMethodInit(tableUpper, table, StringUtils.firstLower(keyType), format));
                    }
                }

                // 构建 Swagger 文档数据 - JSON 数据
                Map<String, Object> swaggerModelMap = new HashMap<>(HASH_MAP_SIZE);
                swaggerModelMap.put("swaggerModels", swaggerModels);
                swaggerModelMap.put("swaggerPathList", swaggerPaths);
                // TODO 生成Swagger文件
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Object> getSwaggerModelPropertiesAndKeyType(ResultSet columnsSet, List<ModelInfo> models, String key, Set<String> typeSet) {
        try {
            String keyType = "";
            Map<String, Object> map = new HashMap<>(HASH_MAP_SIZE);

            // 属性集合
            List<SwaggerModelProperties> swaggerModelProperties = new ArrayList<>();

            while (columnsSet.next()) {
                // 列的描述
                String remarks = columnsSet.getString("REMARKS");
                // 获取列名
                String columnName = columnsSet.getString("COLUMN_NAME");
                // 处理列名
                String propertyName = StringUtils.replace(columnName);
                // 获取类型, 并转成JavaType
                String javaType = JavaTypes.getType(columnsSet.getInt("DATA_TYPE"));
                // 创建该列信息
                models.add(new ModelInfo(
                        javaType,
                        JavaTypes.simpleName(javaType),
                        propertyName,
                        StringUtils.firstUpper(propertyName),
                        remarks,
                        key.equals(columnName),
                        columnName,
                        columnsSet.getString("IS_AUTOINCREMENT")));
                // 需要导包的类型
                typeSet.add(javaType);
                // 主键类型
                if (columnName.equals(key)) {
                    keyType = JavaTypes.simpleName(javaType);
                }
                // SwaggerModelProperties 创建
                SwaggerModelProperties modelProperties = new SwaggerModelProperties();
                modelProperties.setName(propertyName);
                modelProperties.setType(JavaTypes.simpleNameLowerFirst(javaType));
                if ("integer".equals(modelProperties.getType())) {
                    modelProperties.setFormat("int32");
                }
                modelProperties.setDescription(remarks);
                swaggerModelProperties.add(modelProperties);
            }
            map.put("swaggerModelProperties", swaggerModelProperties);
            map.put("keyType", keyType);
            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 初始化方法信息
     * @param tableUpper 模型
     * @param table 表名
     * @param type 主键id类型
     * @param format 格式化
     * @return List
     */
    private static List<SwaggerPath> swaggerMethodInit(String tableUpper, String table, String type, String format) {
        // 集合存储
        List<SwaggerPath> swaggerPaths = new ArrayList<>();

        // 1.add findAll 方法
        SwaggerPath addAndFindAllPath = new SwaggerPath(tableUpper, table);

        // 设置请求路径
        addAndFindAllPath.setPath("/" + table);

        // 初始化方法
        List<SwaggerMethod> swaggerMethods = addAndFindAll(tableUpper, table);
        addAndFindAllPath.setMethods(swaggerMethods);
        swaggerPaths.add(addAndFindAllPath);

        //2.delete    update   findById  方法
        SwaggerPath handlerByIdPath = new SwaggerPath(tableUpper, table);
        //设置请求路径
        handlerByIdPath.setPath("/" + table + "/{id}");
        //初始化方法
        List<SwaggerMethod> handlerByIdMethods = handlerById(tableUpper, table, type, format);
        handlerByIdPath.setMethods(handlerByIdMethods);
        swaggerPaths.add(handlerByIdPath);

        //3.搜索  方法
        SwaggerPath searchPath = new SwaggerPath(tableUpper, table);
        //设置请求路径
        searchPath.setPath("/" + table + "/search");
        //初始化方法
        List<SwaggerMethod> searchMethods = searchMethod(tableUpper, table);
        searchPath.setMethods(searchMethods);
        swaggerPaths.add(searchPath);

        //4.分页条件搜索  方法
        SwaggerPath pageSearchPath = new SwaggerPath(tableUpper, table);
        //设置请求路径
        pageSearchPath.setPath("/" + table + "/search/{page}/{size}");
        //初始化方法
        List<SwaggerMethod> pageSearchMethods = searchPage(tableUpper, table);
        pageSearchPath.setMethods(pageSearchMethods);
        swaggerPaths.add(pageSearchPath);
        return swaggerPaths;
    }

    /***
     * 条件搜索
     * @param tableUpper 模型
     * @param table 表名
     * @return List
     */
    public static List<SwaggerMethod> searchMethod(String tableUpper, String table) {
        //存储所有方法操作
        List<SwaggerMethod> swaggerMethods = new ArrayList<SwaggerMethod>();

        //1.不带分页条件搜索
        SwaggerMethod searchMethod = search(tableUpper, "不带分页条件搜索", "findListUsingPOST", "post");
        //设置请求参数
        searchMethod.setSwaggerParameters(searchPageParameters(tableUpper, 2, 1));
        //响应状态
        searchMethod.setResponses(responses(tableUpper));
        //响应数据配置-200
        SwaggerResponse resp200Search = new SwaggerResponse();
        resp200Search.setCode(200);
        resp200Search.setDescription("不带分页搜索" + tableUpper);
        resp200Search.setSchema("#/definitions/Result«List«" + tableUpper + "»»");
        searchMethod.getResponses().add(resp200Search);
        swaggerMethods.add(searchMethod);

        return swaggerMethods;
    }

    /***
     * 分页和搜索
     * @param tableUpper 模型
     * @param table 表名
     * @return List
     */
    public static List<SwaggerMethod> searchPage(String tableUpper, String table) {
        //存储所有方法操作
        List<SwaggerMethod> swaggerMethods = new ArrayList<SwaggerMethod>();
        //1.分页列表
        SwaggerMethod pageMethod = search(tableUpper, "分页列表查询", "findPageUsingGET", "get");
        //设置请求参数
        pageMethod.setSwaggerParameters(searchPageParameters(tableUpper, 1, 2));
        //响应状态
        pageMethod.setResponses(responses(tableUpper));
        //响应数据配置-200
        SwaggerResponse resp200Page = new SwaggerResponse();
        resp200Page.setCode(200);
        resp200Page.setDescription("不带分页搜索" + tableUpper);
        resp200Page.setSchema("#/definitions/Result«List«" + tableUpper + "»»");
        pageMethod.getResponses().add(resp200Page);
        swaggerMethods.add(pageMethod);

        //2.分页条件搜索
        SwaggerMethod pageSearchMethod = search(tableUpper, "分页条件搜索", "findPageUsingPOST", "post");
        //设置请求参数
        pageSearchMethod.setSwaggerParameters(searchPageParameters(tableUpper, 1, 1));
        //响应状态
        pageSearchMethod.setResponses(responses(tableUpper));
        //响应数据配置-200
        SwaggerResponse resp200PageSearch = new SwaggerResponse();
        resp200PageSearch.setCode(200);
        resp200PageSearch.setDescription("分页条件搜索" + tableUpper);
        resp200PageSearch.setSchema("#/definitions/Result«List«" + tableUpper + "»»");
        pageSearchMethod.getResponses().add(resp200PageSearch);
        swaggerMethods.add(pageSearchMethod);
        return swaggerMethods;
    }

    /***
     * 根据ID删除、修改、查询
     * @param tableUpper 模型
     * @param table 表名
     * @return List
     */
    public static List<SwaggerMethod> handlerById(String tableUpper, String table, String type, String format) {
        //存储所有方法操作
        List<SwaggerMethod> swaggerMethods = new ArrayList<SwaggerMethod>();

        //1.根据ID删除
        SwaggerMethod deleteMethod = delete(tableUpper);
        //设置请求参数
        deleteMethod.setSwaggerParameters(byIdParameters(tableUpper, type, format, "根据ID删除", 1));
        //响应状态
        deleteMethod.setResponses(responses(tableUpper));
        //响应数据配置-200
        SwaggerResponse resp200Delete = new SwaggerResponse();
        resp200Delete.setCode(200);
        resp200Delete.setDescription("根据ID删除" + tableUpper);
        resp200Delete.setSchema("#/definitions/Result");
        deleteMethod.getResponses().add(resp200Delete);
        swaggerMethods.add(deleteMethod);

        //2.根据ID修改
        SwaggerMethod updateMethod = update(tableUpper);
        //设置请求参数
        updateMethod.setSwaggerParameters(byIdParameters(tableUpper, type, format, "根据ID修改", 2));
        //响应状态
        updateMethod.setResponses(responses(tableUpper));
        //响应数据配置-200
        SwaggerResponse resp200Update = new SwaggerResponse();
        resp200Update.setCode(200);
        resp200Update.setDescription("根据ID修改" + tableUpper);
        resp200Update.setSchema("#/definitions/Result");
        updateMethod.getResponses().add(resp200Update);
        swaggerMethods.add(updateMethod);

        //3.根据ID查询
        SwaggerMethod findByIdMethod = findById(tableUpper);
        //设置请求参数
        findByIdMethod.setSwaggerParameters(byIdParameters(tableUpper, type, format, "根据ID修改", 1));
        //响应状态
        findByIdMethod.setResponses(responses(tableUpper));
        //响应数据配置-200
        SwaggerResponse resp200FindById = new SwaggerResponse();
        resp200FindById.setCode(200);
        resp200FindById.setDescription("根据ID修改" + tableUpper);
        resp200FindById.setSchema("#/definitions/Result«" + tableUpper + "»");
        findByIdMethod.getResponses().add(resp200FindById);
        swaggerMethods.add(findByIdMethod);
        return swaggerMethods;
    }

    /***
     * 增加方法和查询所有
     * @param tableUpper 模型
     * @param table 表名
     * @return List
     */
    public static List<SwaggerMethod> addAndFindAll(String tableUpper, String table) {
        //存储所有方法操作
        List<SwaggerMethod> swaggerMethods = new ArrayList<SwaggerMethod>();

        //1.findAll
        SwaggerMethod findAllMethod = findAll(tableUpper);
        //响应状态
        findAllMethod.setResponses(responses(tableUpper));
        //响应数据配置-200
        SwaggerResponse resp200FindAll = new SwaggerResponse();
        resp200FindAll.setCode(200);
        resp200FindAll.setDescription("查询所有" + tableUpper);
        resp200FindAll.setSchema("#/definitions/Result«List«" + tableUpper + "»»");
        findAllMethod.getResponses().add(resp200FindAll);
        swaggerMethods.add(findAllMethod);

        //2.add
        SwaggerMethod addMethod = add(tableUpper);
        //入参
        addMethod.setSwaggerParameters(addParameters(tableUpper));
        //响应状态
        addMethod.setResponses(responses(tableUpper));
        //响应数据配置-200
        SwaggerResponse resp200Add = new SwaggerResponse();
        resp200Add.setCode(200);
        resp200Add.setDescription("添加" + tableUpper);
        resp200Add.setSchema("#/definitions/Result");
        addMethod.getResponses().add(resp200Add);
        swaggerMethods.add(addMethod);
        return swaggerMethods;
    }

    /***
     * 分页搜索
     * @param tableUpper 模型
     * @param isPage:是否分页     1:分页   2：不分
     * @param isSearch:是否条件搜索  1：搜索  2：不搜索
     * @return List
     */
    public static List<SwaggerParameter> searchPageParameters(String tableUpper, int isPage, int isSearch) {
        //集合
        List<SwaggerParameter> swaggerParameterList = new ArrayList<>();
        if (isPage == 1) {
            //当前页
            SwaggerParameter pageParameters = new SwaggerParameter();
            pageParameters.setIn("path");
            pageParameters.setName("page");
            pageParameters.setDescription("当前页");
            pageParameters.setRequired(true);
            pageParameters.setType("integer");
            pageParameters.setFormat("int64");
            swaggerParameterList.add(pageParameters);
            //每页显示条数
            SwaggerParameter sizeParameters = new SwaggerParameter();
            sizeParameters.setIn("path");
            sizeParameters.setName("size");
            sizeParameters.setDescription("每页显示条数");
            sizeParameters.setRequired(true);
            sizeParameters.setType("integer");
            sizeParameters.setFormat("int64");
            swaggerParameterList.add(sizeParameters);
        }

        //是否需要JavaBean参数
        if (isSearch == 1) {
            SwaggerParameter swaggerParameterModel = new SwaggerParameter();
            swaggerParameterModel.setIn("body");
            swaggerParameterModel.setName(tableUpper);
            swaggerParameterModel.setDescription("传入" + tableUpper + "的JSON对象");
            swaggerParameterModel.setRequired(false);
            swaggerParameterModel.setSchema("#/definitions/" + tableUpper);
            swaggerParameterList.add(swaggerParameterModel);
        }
        return swaggerParameterList;
    }

    /***
     * 根据ID操作数据
     * @param tableUpper 模型
     * @param buildModel:是否创建JavaBean对象   1不创建，2创建
     * @return List
     */
    public static List<SwaggerParameter> byIdParameters(String tableUpper, String type, String format, String message, int buildModel) {
        //集合
        List<SwaggerParameter> swaggerParameterList = new ArrayList<SwaggerParameter>();
        //入参
        SwaggerParameter swaggerParameter = new SwaggerParameter();
        swaggerParameter.setIn("path");
        swaggerParameter.setName("id");
        swaggerParameter.setDescription(message + tableUpper + "方法详情");
        swaggerParameter.setRequired(true);
        swaggerParameter.setType(type);
        swaggerParameter.setFormat(format);
        swaggerParameterList.add(swaggerParameter);

        // 是否需要JavaBean参数
        if (buildModel == 2) {
            SwaggerParameter swaggerParameterModel = new SwaggerParameter();
            swaggerParameterModel.setIn("body");
            swaggerParameterModel.setName(tableUpper);
            swaggerParameterModel.setDescription("传入" + tableUpper + "的JSON对象");
            swaggerParameterModel.setRequired(false);
            swaggerParameterModel.setSchema("#/definitions/" + tableUpper);
            swaggerParameterList.add(swaggerParameterModel);
        }
        return swaggerParameterList;
    }

    /***
     * POST添加数据入参
     * @param tableUpper 模型
     * @return List
     */
    public static List<SwaggerParameter> addParameters(String tableUpper) {
        List<SwaggerParameter> swaggerParameterList = new ArrayList<SwaggerParameter>();
        //入参
        SwaggerParameter swaggerParameter = new SwaggerParameter();
        swaggerParameter.setIn("body");
        swaggerParameter.setName("body");
        swaggerParameter.setDescription("添加" + tableUpper + "方法详情");
        swaggerParameter.setRequired(true);
        swaggerParameter.setSchema("#/definitions/" + tableUpper);
        swaggerParameterList.add(swaggerParameter);
        return swaggerParameterList;
    }


    /***
     * 条件搜索
     * @param tableUpper 模型
     * @return SwaggerMethod
     */
    public static SwaggerMethod search(String tableUpper, String message, String opId, String method) {
        SwaggerMethod searchMethod = new SwaggerMethod();
        // searchMethod.setMethod("post");
        searchMethod.setMethod(method);
        searchMethod.setTags(tableUpper + "Controller");
        searchMethod.setSummary(message + tableUpper);
        searchMethod.setDescription(message + tableUpper + "方法详情");
        // searchMethod.setOperationId("findListUsingPOST");
        searchMethod.setOperationId(opId);
        searchMethod.setConsumes("application/json");
        searchMethod.setProduces("application/json");
        return searchMethod;
    }


    /***
     * 根据ID查询方法
     * @param tableUpper 模型
     * @return SwaggerMethod
     */
    public static SwaggerMethod findById(String tableUpper) {
        SwaggerMethod deleteMethod = new SwaggerMethod();
        deleteMethod.setMethod("get");
        deleteMethod.setTags(tableUpper + "Controller");
        deleteMethod.setSummary("根据ID查询" + tableUpper);
        deleteMethod.setDescription("根据ID查询" + tableUpper + "方法详情");
        deleteMethod.setOperationId("findByIdUsingGET");
        deleteMethod.setConsumes("application/json");
        deleteMethod.setProduces("application/json");
        return deleteMethod;
    }

    /***
     * 根据ID修改方法
     * @param tableUpper 模型
     * @return SwaggerMethod
     */
    public static SwaggerMethod update(String tableUpper) {
        SwaggerMethod deleteMethod = new SwaggerMethod();
        deleteMethod.setMethod("put");
        deleteMethod.setTags(tableUpper + "Controller");
        deleteMethod.setSummary("根据ID修改" + tableUpper);
        deleteMethod.setDescription("根据ID修改" + tableUpper + "方法详情");
        deleteMethod.setOperationId("updateUsingPUT");
        deleteMethod.setConsumes("application/json");
        deleteMethod.setProduces("application/json");
        return deleteMethod;
    }

    /***
     * 根据ID删除方法
     * @param tableUpper 模型
     * @return SwaggerMethod
     */
    public static SwaggerMethod delete(String tableUpper) {
        SwaggerMethod deleteMethod = new SwaggerMethod();
        deleteMethod.setMethod("delete");
        deleteMethod.setTags(tableUpper + "Controller");
        deleteMethod.setSummary("根据ID删除" + tableUpper);
        deleteMethod.setDescription("根据ID删除" + tableUpper + "方法详情");
        deleteMethod.setOperationId("deleteUsingDELETE");
        deleteMethod.setConsumes("application/json");
        deleteMethod.setProduces("application/json");
        return deleteMethod;
    }

    /***
     * 添加数据
     * @param tableUpper 模型
     * @return SwaggerMethod
     */
    public static SwaggerMethod add(String tableUpper) {
        SwaggerMethod addMethod = new SwaggerMethod();
        addMethod.setMethod("post");
        addMethod.setTags(tableUpper + "Controller");
        addMethod.setSummary("添加" + tableUpper);
        addMethod.setDescription("添加" + tableUpper + "方法详情");
        addMethod.setOperationId("addUsingPOST");
        addMethod.setConsumes("application/json");
        addMethod.setProduces("application/json");
        return addMethod;
    }


    /***
     * 查询所有
     * @param tableUpper 模型
     * @return SwaggerMethod
     */
    public static SwaggerMethod findAll(String tableUpper) {
        SwaggerMethod findAllMethod = new SwaggerMethod();
        findAllMethod.setMethod("get");
        findAllMethod.setTags(tableUpper + "Controller");
        findAllMethod.setSummary("查询所有" + tableUpper);
        findAllMethod.setDescription("查询所有" + tableUpper + "方法详情");
        findAllMethod.setOperationId("findAllUsingGET");
        findAllMethod.setConsumes("application/json");
        findAllMethod.setProduces("application/json");
        return findAllMethod;
    }

    /***
     * 响应状态
     * @param tableUpper 模型
     * @return List
     */
    public static List<SwaggerResponse> responses(String tableUpper) {
        List<SwaggerResponse> responses = new ArrayList<>();
        //响应数据配置-400
        SwaggerResponse resp400 = new SwaggerResponse();
        resp400.setCode(400);
        resp400.setDescription("Invalid status value(无效的状态值)");
        //响应数据配置-403
        SwaggerResponse resp403 = new SwaggerResponse();
        resp403.setCode(403);
        resp403.setDescription("403 Forbidden(请求被拒绝)");
        //响应数据配置-404
        SwaggerResponse resp404 = new SwaggerResponse();
        resp404.setCode(404);
        resp404.setDescription("not found(没有找到相关资源)");
        //响应数据配置-405
        SwaggerResponse resp405 = new SwaggerResponse();
        resp405.setCode(405);
        resp405.setDescription("Invalid input(无效的输入)");
        //响应数据配置-500
        SwaggerResponse resp500 = new SwaggerResponse();
        resp500.setCode(500);
        resp500.setDescription("服务器内部错误");
        responses.add(resp400);
        responses.add(resp403);
        responses.add(resp404);
        responses.add(resp405);
        responses.add(resp500);
        return responses;
    }
}
