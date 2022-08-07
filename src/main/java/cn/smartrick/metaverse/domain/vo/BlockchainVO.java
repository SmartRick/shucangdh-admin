package cn.smartrick.metaverse.domain.vo;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 *  [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c) SmartRickInc. All rights reserved.
 * @date  2022-07-27 10:26:37
 * @since JDK1.8
 */
@Data
public class BlockchainVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("blockchain")
    private String blockchain;

    @ApiModelProperty("remark")
    private String remark;

    @ApiModelProperty("link")
    private String link;

    @ApiModelProperty("background_color")
    private String backgroundColor;


}
