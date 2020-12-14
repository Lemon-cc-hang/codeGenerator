package com.test.dao;

import com.test.model.Brand;
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
public interface BrandDao extends BaseMapper<Brand>{
}