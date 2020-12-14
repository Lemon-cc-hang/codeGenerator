package com.test.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
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
@ApiModel(description = "Sku",value = "Sku")
public class Sku implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    @TableId
    private Long id;

    @ApiModelProperty(value = "商品条码")
    private String sn;

    @ApiModelProperty(value = "SKU名称")
    private String name;

    @ApiModelProperty(value = "价格（分）")
    private Integer price;

    @ApiModelProperty(value = "库存数量")
    private Integer num;

    @ApiModelProperty(value = "库存预警数量")
    private Integer alertNum;

    @ApiModelProperty(value = "商品图片")
    private String image;

    @ApiModelProperty(value = "商品图片列表")
    private String images;

    @ApiModelProperty(value = "重量（克）")
    private Integer weight;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "SPUID")
    private Long spuId;

    @ApiModelProperty(value = "类目ID")
    private Integer categoryId;

    @ApiModelProperty(value = "类目名称")
    private String categoryName;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "规格")
    private String spec;

    @ApiModelProperty(value = "销量")
    private Integer saleNum;

    @ApiModelProperty(value = "评论数")
    private Integer commentNum;

    @ApiModelProperty(value = "商品状态 1-正常，2-下架，3-删除")
    private String status;

}