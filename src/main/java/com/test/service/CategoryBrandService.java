package com.test.service;

import com.test.model.CategoryBrand;
import com.test.dao.CategoryBrandDao;
import com.test.api.CategoryBrandApi;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @since 2020-12-14
 */
@Service
public class CategoryBrandService extends ServiceImpl<CategoryBrandDao, CategoryBrand> implements CategoryBrandApi {

}
