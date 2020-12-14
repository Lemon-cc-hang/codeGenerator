package com.test.feign;

import com.test.api.SkuApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 *     feign
 * </p>
 *
 * @since 2020-12-14
 */
@FeignClient("service")
public interface SkuFeignClient extends SkuApi {
}