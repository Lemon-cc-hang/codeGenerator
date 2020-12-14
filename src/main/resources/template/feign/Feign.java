package ${package_feign};

import ${package_service}.${tableUpper}${package_service_suffix};
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 *     feign
 * </p>
 *
 <#if author??>
 * @author ${author}
</#if>
 * @since ${date}
 */
<#if serviceName??>
@FeignClient("${serviceName}")
    <#else>
@FeignClient("")
</#if>
public interface ${tableUpper}${package_feign_suffix} extends ${tableUpper}${package_service_suffix} {
}