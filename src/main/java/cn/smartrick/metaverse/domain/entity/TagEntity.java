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
@TableName("t_tag")
public class TagEntity extends BaseEntity implements Serializable{

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    /**
     * tag_name
     */
    @ApiModelProperty("tag_name")
    private String tagName;


    /**
     * tag_type
     */
    @ApiModelProperty("tag_type")
    private Integer tagType;


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
