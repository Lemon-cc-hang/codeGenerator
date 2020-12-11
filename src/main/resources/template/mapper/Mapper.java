package ${package_mapper};

import ${package_entity}.${tableUpper};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *     Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Mapper
@Repository
public interface ${tableUpper}Mapper extends BaseMapper<${tableUpper}>{
}