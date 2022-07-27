package cn.smartrick.metaverse.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.smartrick.metaverse.common.domain.BaseEntity;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
    private String marketModel;


    /**
     * browse_num
     */
    @ApiModelProperty("browse_num")
    private Integer browseNum;


    /**
     * lick_num
     */
    @ApiModelProperty("lick_num")
    private Integer lickNum;


}
