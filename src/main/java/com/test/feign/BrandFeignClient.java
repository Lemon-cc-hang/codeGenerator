package com.test.feign;

import com.test.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 *     feign
 * </p>
 *
 * @since 2020-12-14
 */
@FeignClient("service")
public interface BrandFeignClient extends BrandApi {
}