package cn.smartrick.metaverse.domain.entity;

import cn.smartrick.metaverse.common.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * [ 数藏平台评论关联表 ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c)  SmartRickInc. All rights reserved.
 * @date 2022-08-27 16:20:10
 * @since JDK1.8
 */
@Data
@TableName("t_sc_cm")
public class ScCmEntity extends BaseEntity implements Serializable{

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    /**
     * platform_id
     */
    @ApiModelProperty("platform_id")
    private Long platformId;


    /**
     * comment_id
     */
    @ApiModelProperty("comment_id")
    private Long commentId;


}
