package ${package_feign};

import ${package_rsp};
import ${package_entity}.${tableUpper};
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *     feign
 * </p>
 *
 <#if author??>
 * @author ${author}
</#if>
 * @since ${date}
 */
<#if serviceName??>
@FeignClient("${serviceName}")
    <#else>
@FeignClient("")
</#if>
public interface ${tableUpper}${package_feign_suffix} {

    /**
     * 查询所有${tableUpper}${package_entity_suffix}
     * @return 实体
     */
    @GetMapping("/findAll")
    ${package_rsp_name} findAll();

    /**
     * 根据id查询
     * @param id id
     * @return 实体
     */
    @GetMapping( "/{id}")
    ${package_rsp_name} findById(@PathVariable("id") Long id);

    /**
     * 增加或更新数据
     * @param ${table} ${tableUpper}${package_entity_suffix}
     * @return 实体
     */
    @PostMapping({"/update", "/add"})
    ${package_rsp_name} update(@RequestBody ${tableUpper}${package_entity_suffix} ${table});

    /**
     * 删除数据
     * @param id id
     * @return 实体
     */
    @GetMapping("/delete/{id}")
    ${package_rsp_name} delete(@PathVariable("id") Long id);

    /**
     * 分页查询
     * @param page 当前页面
     * @param size 每页显示的数量
     * @return 实体
     */
    @GetMapping("/{page}/{size}")
    ${package_rsp_name} findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size);

    /**
     * 分页查询
     * @param page 当前页面
     * @param size 每页显示的数量
     * @return 实体
     */
    @GetMapping
    ${package_rsp_name} findPage1(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size);
}