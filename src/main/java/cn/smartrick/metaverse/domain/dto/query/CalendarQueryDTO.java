package cn.smartrick.metaverse.domain.dto.query;

import cn.smartrick.metaverse.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

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
public class CalendarQueryDTO extends PageParamDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("藏品名称")
    private String name;

    @ApiModelProperty("cover_img")
    private String coverImg;

    @ApiModelProperty("藏品状态：1：待发售，2：发售中，3：发售结束")
    private Integer state;

    @ApiModelProperty("发布藏品所在的平台ID")
    private Long platfromId;

    @ApiModelProperty("发售作者")
    private String releaseAuthor;

    @ApiModelProperty("发售数量")
    private Integer releaseNum;

    @ApiModelProperty("发售时间")
    private Date releaseTime;

    @ApiModelProperty("藏品价格")
    private BigDecimal price;

    @ApiModelProperty("如果商品的链接为空默认跳转到数藏平台官方地址")
    private String goodsUrl;

    @ApiModelProperty("created_at")
    private Date createdAt;

    @ApiModelProperty("updated_at")
    private Date updatedAt;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间-截止")
    private Date createTimeEnd;

    @ApiModelProperty("上次更新时间-开始")
    private Date updateTimeBegin;

    @ApiModelProperty("上次更新创建时间-开始")
    private Date updateTimeEnd;
}
