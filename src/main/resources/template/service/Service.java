package ${package_service};

import ${package_entity}.${tableUpper}${package_entity_suffix};
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *     服务类
 * </p>
 *
 <#if author??>
 * @author ${author}
</#if>
 * @since ${date}
 */
public interface ${tableUpper}${package_service_suffix} extends IService<${tableUpper}${package_entity_suffix}> {

}