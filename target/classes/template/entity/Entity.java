package ${package_entity};
<#if swagger==true>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
<#list typeSet as set>
import ${set};
</#list>
/**
 * <p>
 *     实体类
 * </p>
 *
 <#if author??>
 * @author ${author}
</#if>
 * @since ${date}
 */
@Data
@EqualsAndHashCode
<#if swagger==true>
@ApiModel(description = "${tableUpper}",value = "${tableUpper}")
</#if>
public class ${tableUpper} implements Serializable {

    private static final long serialVersionUID = 1L;

<#list models as model>
    <#if swagger==true>
    @ApiModelProperty(value = "${model.desc!""}")
    </#if>
    <#if model.id==true>
    @TableId<#if model.identity='YES'>(type = IdType.AUTO)</#if>
    </#if>
    private ${model.simpleType} ${model.name};

</#list>
}