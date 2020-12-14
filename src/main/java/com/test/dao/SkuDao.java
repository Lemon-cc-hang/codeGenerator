package com.test.dao;

import com.test.model.Sku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *     Mapper 接口
 * </p>
 *
 * @since 2020-12-14
 */
@Mapper
@Repository
public interface SkuDao extends BaseMapper<Sku>{
}