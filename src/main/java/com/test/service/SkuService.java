package com.test.service;

import com.test.model.Sku;
import com.test.dao.SkuDao;
import com.test.api.SkuApi;
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
public class SkuService extends ServiceImpl<SkuDao, Sku> implements SkuApi {

}
