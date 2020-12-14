package com.test.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.model.CategoryBrand;
import com.test.api.CategoryBrandApi;
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
@Api(value = "CategoryBrandController")
@RestController
@RequestMapping("/categoryBrand")
public class CategoryBrandController {

    @Autowired
    private CategoryBrandApi categoryBrandApi;

    /**
     * 查询所有CategoryBrand
     * @return 实体
     */
    @ApiOperation("查询所有CategoryBrand")
    @RequestMapping(value = "/findAll", method = {RequestMethod.GET})
    public List<CategoryBrand> findAll() {
        return categoryBrandApi.list();
    }

    /**
     * 根据id查询
     * @param id id
     * @return 实体
     */
    @ApiOperation("根据id查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    public CategoryBrand findById(@PathVariable("id") Long id) {
        return categoryBrandApi.getById(id);
    }

    /**
     * 增加或更新数据
     * @param categoryBrand CategoryBrand
     * @return 实体
     */
    @ApiOperation("增加或更新数据")
    @RequestMapping(value = {"/update", "/add"}, method = {RequestMethod.POST})
    public boolean update(@RequestBody @ApiParam(name = "CategoryBrand对象",value = "传入JSON数据") CategoryBrand categoryBrand) {
        return categoryBrandApi.saveOrUpdate(categoryBrand);
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
        return categoryBrandApi.removeById(id);
    }

    /**
     * 分页查询
     * @param page 当前页面
     * @param size 每页显示的数量
     * @return 实体
     */
    @ApiOperation(value = "CategoryBrand条件分页查询",notes = "分页条件查询CategoryBrand方法详情",tags = {"CategoryBrandController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/{page}/{size}", method = {RequestMethod.GET})
    public IPage<User> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        if (page < 1) {
            page = 1;
        }
        Page<CategoryBrand> pages = new Page<>(page, size);
        return categoryBrandApi.page(pages);
    }

    /**
     * 分页查询
     * @param page 当前页面
     * @param size 每页显示的数量
     * @return 实体
     */
    @ApiOperation(value = "CategoryBrand条件分页查询",notes = "分页条件查询CategoryBrand方法详情",tags = {"CategoryBrandController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public IPage<CategoryBrand> findPage1(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer size) {
        if (page < 1) {
            page = 1;
        }
        Page<CategoryBrand> pages = new Page<>(page, size);
        return categoryBrandApi.page(pages);
    }
}

