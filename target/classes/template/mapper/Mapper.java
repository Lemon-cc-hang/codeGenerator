package ${package_mapper};

import ${package_entity}.${tableUpper}${package_entity_suffix};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *     Mapper 接口
 * </p>
 *
 <#if author??>
 * @author ${author}
</#if>
 * @since ${date}
 */
@Mapper
@Repository
public interface ${tableUpper}${package_mapper_suffix} extends BaseMapper<${tableUpper}${package_entity_suffix}>{
}