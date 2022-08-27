package cn.smartrick.metaverse.domain.dto.add;

import lombok.Data;
import java.util.Date;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c) 2018 SmartRickInc. All rights reserved.
 * @date  2022-08-27 16:20:10
 * @since JDK1.8
 */
@Data
public class CalendarAddDTO {
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date releaseTime;

    @ApiModelProperty("藏品价格")
    private BigDecimal price;

    @ApiModelProperty("如果商品的链接为空默认跳转到数藏平台官方地址")
    private String goodsUrl;


}
