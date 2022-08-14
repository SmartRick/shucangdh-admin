package cn.smartrick.metaverse.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c) SmartRickInc. All rights reserved.
 * @date 2022-07-27 10:26:37
 * @since JDK1.8
 */
@Data
public class ShucangPlatformVO {

    @ApiModelProperty("id")
    private Integer id;

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

    /**
     * 是否审查通过了
     */
    @ApiModelProperty("reviewed")
    private Boolean reviewed;

    @ApiModelProperty("tag_list")
    private List<TagVO> tagList;

    @ApiModelProperty("blockchain_list")
    private List<Integer> blockchainList;


}
