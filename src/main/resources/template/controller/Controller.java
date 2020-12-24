package ${package_controller};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package_entity}.${tableUpper}${package_entity_suffix};
import ${package_service}.${tableUpper}${package_service_suffix};
import ${package_rsp};
<#if swagger==true>
import io.swagger.annotations.*;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
<#if author??>
 * @author ${author}
</#if>
 * @since ${date}
 */
<#if swagger==true>
@Api(value = "${tableUpper}${package_controller_suffix}")
</#if>
@RestController
@RequestMapping("/${table}")
public class ${tableUpper}${package_controller_suffix} {

    static Logger logger = LoggerFactory.getLogger(${tableUpper}${package_controller_suffix}.class);

    @Autowired
    private ${tableUpper}${package_service_suffix} ${table}${package_service_suffix};

    /**
     * 查询所有${tableUpper}${package_entity_suffix}
     * @return 实体
     */
    <#if swagger==true>
    @ApiOperation("查询所有${tableUpper}${package_entity_suffix}")
    </#if>
    @RequestMapping(value = "/findAll", method = {RequestMethod.GET})
    public ${package_rsp_name} findAll() {
        return ${package_rsp_name}.success(${table}${package_service_suffix}.list());
    }

    /**
     * 根据id查询
     * @param id id
     * @return 实体
     */
    <#if swagger==true>
    @ApiOperation("根据id查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "${keyType}")
    </#if>
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public ${package_rsp_name} findById(@PathVariable("id") String id) {
        if (id == null) {
            logger.error("id is null");
            return ${package_rsp_name}.invalidParameter();
        }
        return ${package_rsp_name}.success(${table}${package_service_suffix}.getById(id));
    }

    /**
     * 增加或更新数据
     * @param ${table} ${tableUpper}${package_entity_suffix}
     * @return 实体
     */
    <#if swagger==true>
    @ApiOperation("增加或更新数据")
    </#if>
    @RequestMapping(value = {"/update", "/add"}, method = {RequestMethod.POST})
    public ${package_rsp_name} update(@RequestBody <#if swagger==true>@ApiParam(name = "${tableUpper}${package_entity_suffix}对象",value = "传入JSON数据")</#if> ${tableUpper}${package_entity_suffix} ${table}) {
        if (${table} == null) {
            logger.error("${table} is null");
            return ${package_rsp_name}.invalidParameter();
        }
        if (!${table}${package_service_suffix}.saveOrUpdate(${table})) {
            logger.error("update or add ${table} error");
            return ${package_rsp_name}.error(${package_rsp_name}.RSP_CODE__ERROR);
        }
        return ${package_rsp_name}.success();
    }

    /**
     * 删除数据
     * @param id id
     * @return 实体
     */
    <#if swagger==true>
    @ApiOperation("删除数据")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "${keyType}")
    </#if>
    @RequestMapping(value = {"/delete/{id}"}, method = {RequestMethod.GET})
    public ${package_rsp_name} delete(@PathVariable("id") String id) {
        if (id == null) {
            logger.error("id is null");
            return ${package_rsp_name}.invalidParameter();
        }
        if (!${table}${package_service_suffix}.removeById(id)) {
           logger.error("delete ${table} error");
            return ${package_rsp_name}.error(${package_rsp_name}.RSP_CODE__ERROR);
        }
        return ${package_rsp_name}.success();
    }

    /**
     * 分页查询
     * @param page 当前页面
     * @param size 每页显示的数量
     * @return 实体
     */
    <#if swagger==true>
    @ApiOperation(value = "${tableUpper}${package_entity_suffix}分页查询",notes = "分页查询${tableUpper}${package_entity_suffix}方法详情",tags = {"${tableUpper}Controller"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    </#if>
    @RequestMapping(value = "/{page}/{size}", method = {RequestMethod.GET})
    public ${package_rsp_name} findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        if (page < 1) {
            page = 1;
        }
        Page<${tableUpper}${package_entity_suffix}> pages = new Page<>(page, size);
        return ${package_rsp_name}.success(${table}${package_service_suffix}.page(pages));
    }

    /**
     * 分页查询
     * @param page 当前页面
     * @param size 每页显示的数量
     * @return 实体
     */
    <#if swagger==true>
    @ApiOperation(value = "${tableUpper}${package_entity_suffix}分页查询",notes = "分页查询${tableUpper}${package_entity_suffix}方法详情",tags = {"${tableUpper}Controller"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    </#if>
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public ${package_rsp_name} findPage1(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size) {
        if (page < 1) {
            page = 1;
        }
        Page<${tableUpper}${package_entity_suffix}> pages = new Page<>(page, size);
        return ${package_rsp_name}.success(${table}${package_service_suffix}.page(pages));
    }
}

