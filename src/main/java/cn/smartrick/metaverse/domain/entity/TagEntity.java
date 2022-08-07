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
     * 标签类型0 客户端、1 天眼查
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


    //客户端类型
    public static final int TAG_TYPE_CLIENT = 1;
    //背景信息标签
    public static final int TAG_TYPE_BACKGROUND = 2;
    //服务公众号
    public static final int TAG_TYPE_SERVER = 3;
    //小程序
    public static final int TAG_TYPE_LITTLE_PROGRAM = 4;

}
