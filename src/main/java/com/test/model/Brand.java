package com.test.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.lang.String;
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
@ApiModel(description = "Brand",value = "Brand")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "品牌id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "品牌名称")
    private String name;

    @ApiModelProperty(value = "品牌图片地址")
    private String image;

    @ApiModelProperty(value = "品牌的首字母")
    private String letter;

    @ApiModelProperty(value = "排序")
    private Integer seq;

}