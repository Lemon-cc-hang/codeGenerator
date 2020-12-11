package ${package_entity};
<#if swagger==true>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
<#list typeSet as set>
import ${set}
</#list>
/**
 * @auther ${author}
 * @description ${tableUpper} 构建
 * @date
 *
 */
<#if swagger==true>
@ApiModel(description = "${tableUpper}",value = "${tableUpper}")
</#if>
@Table(name="${tableName}")
public class ${tableUpper} implements Serializable{
<#list models as model>
    <#if swagger==true>
    @ApiModelProperty(value = "${model.desc!""}", required = false)
    </#if>
    <#if model.id==true>
    @TableId
        <#if model.identity=='YES'>
        (type = IdType.AUTO)
        </#if>
    </#if>
    private ${model.simpleType} ${model.name};
</#list>
}