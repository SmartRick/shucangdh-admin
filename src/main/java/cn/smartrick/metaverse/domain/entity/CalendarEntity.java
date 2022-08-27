package cn.smartrick.metaverse.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.smartrick.metaverse.common.domain.BaseEntity;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c)  SmartRickInc. All rights reserved.
 * @date 2022-08-27 16:20:10
 * @since JDK1.8
 */
@Data
@TableName("t_calendar")
public class CalendarEntity extends BaseEntity implements Serializable{

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    /**
     * 藏品名称
     */
    @ApiModelProperty("藏品名称")
    private String name;


    /**
     * cover_img
     */
    @ApiModelProperty("cover_img")
    private String coverImg;


    /**
     * 藏品状态：1：待发售，2：发售中，3：发售结束
     */
    @ApiModelProperty("藏品状态：1：待发售，2：发售中，3：发售结束")
    private Integer state;


    /**
     * 发布藏品所在的平台ID
     */
    @ApiModelProperty("发布藏品所在的平台ID")
    private Long platfromId;


    /**
     * 发售作者
     */
    @ApiModelProperty("发售作者")
    private String releaseAuthor;


    /**
     * 发售数量
     */
    @ApiModelProperty("发售数量")
    private Integer releaseNum;


    /**
     * 发售时间
     */
    @ApiModelProperty("发售时间")
    private Date releaseTime;


    /**
     * 藏品价格
     */
    @ApiModelProperty("藏品价格")
    private BigDecimal price;


    /**
     * 如果商品的链接为空默认跳转到数藏平台官方地址
     */
    @ApiModelProperty("如果商品的链接为空默认跳转到数藏平台官方地址")
    private String goodsUrl;


}
