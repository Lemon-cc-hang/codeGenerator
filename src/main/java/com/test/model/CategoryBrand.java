package com.test.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.lang.Integer;
/**
 * <p>
 *     实体类
 * </p>
 *
 * @since 2020-12-14
 */
@Data
@EqualsAndHashCode
@ApiModel(description = "CategoryBrand",value = "CategoryBrand")
public class CategoryBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类ID")
    private Integer categoryId;

    @ApiModelProperty(value = "品牌ID")
    @TableId
    private Integer brandId;

}