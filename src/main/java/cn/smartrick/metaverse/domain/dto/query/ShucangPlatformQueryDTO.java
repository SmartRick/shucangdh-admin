package cn.smartrick.metaverse.domain.dto.query;

import cn.smartrick.metaverse.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c)  SmartRickInc. All rights reserved.
 * @date 2022-07-27 10:26:37
 * @since JDK1.8
 */
@Data
public class ShucangPlatformQueryDTO extends PageParamDTO {

    @ApiModelProperty("name")
    private String name;

    @ApiModelProperty("cover_img")
    private String coverImg;

    @ApiModelProperty("remark")
    private String remark;

    @ApiModelProperty("market_model")
    private String marketModel;

    @ApiModelProperty("标签名称")
    private String tagName;

    /**
     * 是否审查通过了
     */
    @ApiModelProperty("reviewed")
    private Boolean reviewed;

    @ApiModelProperty("区块链")
    private List<Integer> blockchainList;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间-截止")
    private Date createTimeEnd;

    @ApiModelProperty("上次更新时间-开始")
    private Date updateTimeBegin;

    @ApiModelProperty("上次更新创建时间-开始")
    private Date updateTimeEnd;
}
