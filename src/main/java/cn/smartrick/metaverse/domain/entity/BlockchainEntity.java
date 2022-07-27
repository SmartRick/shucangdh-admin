package cn.smartrick.metaverse.domain.entity;

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
@TableName("t_blockchain")
public class BlockchainEntity extends BaseEntity implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    /**
     * blockchain
     */
    @ApiModelProperty("blockchain")
    private String blockchain;


    /**
     * remark
     */
    @ApiModelProperty("remark")
    private String remark;


    /**
     * link
     */

    @ApiModelProperty("link")
    private String link;


    /**
     * background_color
     */
    @ApiModelProperty("background_color")
    private String backgroundColor;


}
