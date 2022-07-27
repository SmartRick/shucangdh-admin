package cn.smartrick.metaverse.domain.dto.add;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c) 2018 SmartRickInc. All rights reserved.
 * @date  2022-07-27 10:26:37
 * @since JDK1.8
 */
@Data
public class ShucangPlatformAddDTO {
    @ApiModelProperty("name")
    private String name;

    @ApiModelProperty("cover_img")
    private String coverImg;

    @ApiModelProperty("remark")
    private String remark;

    @ApiModelProperty("market_model")
    private String marketModel;

    @ApiModelProperty("browse_num")
    private Integer browseNum;

    @ApiModelProperty("lick_num")
    private Integer lickNum;


}
