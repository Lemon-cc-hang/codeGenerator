package ${package_service_impl};

import ${package_entity}.${tableUpper}${package_entity_suffix};
import ${package_mapper}.${tableUpper}${package_mapper_suffix};
import ${package_service}.${tableUpper}${package_service_suffix};
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 <#if author??>
 * @author ${author}
</#if>
 * @since ${date}
 */
@Service
public class ${tableUpper}${package_service_impl_suffix} extends ServiceImpl<${tableUpper}${package_mapper_suffix}, ${tableUpper}${package_entity_suffix}> implements ${tableUpper}${package_service_suffix} {

}
