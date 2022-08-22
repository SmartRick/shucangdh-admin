package cn.smartrick.metaverse.domain.entity;

import cn.smartrick.metaverse.common.constant.MarketModel;
import cn.smartrick.metaverse.common.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c)  SmartRickInc. All rights reserved.
 * @date 2022-07-27 10:55:42
 * @since JDK1.8
 */
@Data
@TableName("t_shucang_platform")
public class ShucangPlatformEntity extends BaseEntity implements Serializable{

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    /**
     * name
     */
    @ApiModelProperty("name")
    private String name;


    /**
     * cover_img
     */
    @ApiModelProperty("cover_img")
    private String coverImg;


    /**
     * remark
     */
    @ApiModelProperty("remark")
    private String remark;


    /**
     * market_model
     */
    @ApiModelProperty("market_model")
    private Integer marketModel = MarketModel.MODEL_ISSUE.getCode();


    /**
     * browse_num
     */
    @ApiModelProperty("browse_num")
    private Integer browseNum;


    /**
     * lick_num
     */
    @ApiModelProperty("like_num")
    private Integer likeNum;

    /**
     * 是否审查通过了
     */
    @ApiModelProperty("reviewed")
    private Boolean reviewed;

}
