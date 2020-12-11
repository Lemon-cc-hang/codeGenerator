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
 *     ${tableUpper} 构建
 * </p>
 *
 * @author ${author}
 * @since ${date}
 *
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
    <#if model.id==true && model.identity='YES'>
    @TableId(type = IdType.AUTO)
    <#elseif model.id==true>
    @TableId
    </#if>
    private ${model.simpleType} ${model.name};

</#list>
}