package com.test.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.model.Brand;
import com.test.api.BrandApi;
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
@Api(value = "BrandController")
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandApi brandApi;

    /**
     * 查询所有Brand
     * @return 实体
     */
    @ApiOperation("查询所有Brand")
    @RequestMapping(value = "/findAll", method = {RequestMethod.GET})
    public List<Brand> findAll() {
        return brandApi.list();
    }

    /**
     * 根据id查询
     * @param id id
     * @return 实体
     */
    @ApiOperation("根据id查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public Brand findById(@PathVariable("id") Long id) {
        return brandApi.getById(id);
    }

    /**
     * 增加或更新数据
     * @param brand Brand
     * @return 实体
     */
    @ApiOperation("增加或更新数据")
    @RequestMapping(value = {"/update", "/add"}, method = {RequestMethod.POST})
    public boolean update(@RequestBody @ApiParam(name = "Brand对象",value = "传入JSON数据") Brand brand) {
        return brandApi.saveOrUpdate(brand);
    }

    /**
     * 删除数据
     * @param id id
     * @return 实体
     */
    @ApiOperation("删除数据")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @RequestMapping(value = {"/delete/{id}"}, method = {RequestMethod.GET})
    public boolean delete(@PathVariable("id") Long id) {
        return brandApi.removeById(id);
    }

    /**
     * 分页查询
     * @param page 当前页面
     * @param size 每页显示的数量
     * @return 实体
     */
    @ApiOperation(value = "Brand条件分页查询",notes = "分页条件查询Brand方法详情",tags = {"BrandController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/{page}/{size}", method = {RequestMethod.GET})
    public IPage<User> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        if (page < 1) {
            page = 1;
        }
        Page<Brand> pages = new Page<>(page, size);
        return brandApi.page(pages);
    }

    /**
     * 分页查询
     * @param page 当前页面
     * @param size 每页显示的数量
     * @return 实体
     */
    @ApiOperation(value = "Brand条件分页查询",notes = "分页条件查询Brand方法详情",tags = {"BrandController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public IPage<Brand> findPage1(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size) {
        if (page < 1) {
            page = 1;
        }
        Page<Brand> pages = new Page<>(page, size);
        return brandApi.page(pages);
    }
}

