package com.test.service;

import com.test.model.Brand;
import com.test.dao.BrandDao;
import com.test.api.BrandApi;
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
public class BrandService extends ServiceImpl<BrandDao, Brand> implements BrandApi {

}
