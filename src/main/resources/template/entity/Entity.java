package ${package_entity};

<#if swagger==true>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@Accessors(chain = true)
<#if swagger==true>
@ApiModel(description = "${tableUpper}${package_entity_suffix}",value = "${tableUpper}${package_entity_suffix}")
</#if>
@TableName("${tableName}")
public class ${tableUpper}${package_entity_suffix} implements Serializable {

    private static final long serialVersionUID = 1L;

<#list models as model>
    <#if swagger==true>
    @ApiModelProperty(value = "${model.desc!""}")
    </#if>
    /**
     * ${model.desc!""}
     */
    <#if model.id==true>
    @TableId(type = ${idType}, value = "${model.column}")
    <#else>
    @TableField(value = "${model.column}")
    </#if>
    private ${model.simpleType} ${model.name};

</#list>
}