package com.test.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.model.Sku;
import com.test.api.SkuApi;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @since 2020-12-14
 */
@Api(value = "SkuController")
@RestController
@RequestMapping("/sku")
public class SkuController {

    @Autowired
    private SkuApi skuApi;

    /**
     * 查询所有Sku
     * @return 实体
     */
    @ApiOperation("查询所有Sku")
    @RequestMapping(value = "/findAll", method = {RequestMethod.GET})
    public List<Sku> findAll() {
        return skuApi.list();
    }

    /**
     * 根据id查询
     * @param id id
     * @return 实体
     */
    @ApiOperation("根据id查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public Sku findById(@PathVariable("id") Long id) {
        return skuApi.getById(id);
    }

    /**
     * 增加或更新数据
     * @param sku Sku
     * @return 实体
     */
    @ApiOperation("增加或更新数据")
    @RequestMapping(value = {"/update", "/add"}, method = {RequestMethod.POST})
    public boolean update(@RequestBody @ApiParam(name = "Sku对象",value = "传入JSON数据") Sku sku) {
        return skuApi.saveOrUpdate(sku);
    }

    /**
     * 删除数据
     * @param id id
     * @return 实体
     */
    @ApiOperation("删除数据")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @RequestMapping(value = {"/delete/{id}"}, method = {RequestMethod.GET})
    public boolean delete(@PathVariable("id") Long id) {
        return skuApi.removeById(id);
    }

    /**
     * 分页查询
     * @param page 当前页面
     * @param size 每页显示的数量
     * @return 实体
     */
    @ApiOperation(value = "Sku条件分页查询",notes = "分页条件查询Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/{page}/{size}", method = {RequestMethod.GET})
    public IPage<User> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        if (page < 1) {
            page = 1;
        }
        Page<Sku> pages = new Page<>(page, size);
        return skuApi.page(pages);
    }

    /**
     * 分页查询
     * @param page 当前页面
     * @param size 每页显示的数量
     * @return 实体
     */
    @ApiOperation(value = "Sku条件分页查询",notes = "分页条件查询Sku方法详情",tags = {"SkuController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public IPage<Sku> findPage1(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size) {
        if (page < 1) {
            page = 1;
        }
        Page<Sku> pages = new Page<>(page, size);
        return skuApi.page(pages);
    }
}

