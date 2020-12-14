package com.test.feign;

import com.test.api.CategoryBrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 *     feign
 * </p>
 *
 * @since 2020-12-14
 */
@FeignClient("service")
public interface CategoryBrandFeignClient extends CategoryBrandApi {
}