package ${package_controller}


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package_entity}.${tableUpper};
import ${package_service}.${tableUpper}Service;
<#if swagger==true>
import io.swagger.annotations.*;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if swagger==true>
@Api(value = "${tableUpper}Controller")
</#if>
@RestController
@RequestMapping("/${table}")
public class ${tableUpper}Controller {

    @Autowired
    private ${tableUpper}Service ${table}Service;

    /**
     * 查询所有${tableUpper}
     * @return 实体
     */
    <#if swagger==true>
    @ApiOperation("查询所有${tableUpper}")
    </#if>
    @RequestMapping(value = "/findAll", method = {RequestMethod.GET})
    public List<${tableUpper}> findAll() {
        return ${table}Service.list();
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
    public ${tableUpper} findById(@PathVariable("id") Long id) {
        return ${table}Service.getById(id);
    }

    /**
     * 增加或更新数据
     * @param ${table} ${tableUpper}
     * @return 实体
     */
    <#if swagger==true>
    @ApiOperation("增加或更新数据")
    </#if>
    @RequestMapping(value = {"/update", "/add"}, method = {RequestMethod.POST})
    public boolean update(@RequestBody <#if swagger==true>@ApiParam(name = "${tableUpper}对象",value = "传入JSON数据")</#if> ${tableUpper} ${table}) {
        return ${table}Service.saveOrUpdate(${table});
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
    public boolean delete(@PathVariable("id") Long id) {
        return ${table}Service.removeById(id);
    }

    /**
     * 分页查询
     * @param page 当前页面
     * @param size 每页显示的数量
     * @return 实体
     */
    <#if swagger==true>
    @ApiOperation(value = "${tableUpper}条件分页查询",notes = "分页条件查询${tableUpper}方法详情",tags = {"${tableUpper}Controller"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    </#if>
    @RequestMapping(value = "/{page}/{size}", method = {RequestMethod.GET})
    public IPage<User> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        if (page < 1) {
            page = 1;
        }
        Page<${tableUpper}> pages = new Page<>(page, size);
        return ${table}Service.page(pages);
    }

    /**
     * 分页查询
     * @param page 当前页面
     * @param size 每页显示的数量
     * @return 实体
     */
    <#if swagger==true>
    @ApiOperation(value = "${tableUpper}条件分页查询",notes = "分页条件查询${tableUpper}方法详情",tags = {"${tableUpper}Controller"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    </#if>
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public IPage<${tableUpper}> findPage1(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size) {
        if (page < 1) {
            page = 1;
        }
        Page<${tableUpper}> pages = new Page<>(page, size);
        return ${table}Service.page(pages);
    }
}

