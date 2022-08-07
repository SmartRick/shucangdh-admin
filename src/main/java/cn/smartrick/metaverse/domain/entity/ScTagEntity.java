package cn.smartrick.metaverse.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

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
@AllArgsConstructor
@TableName("t_sc_tag")
public class ScTagEntity implements Serializable{

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public ScTagEntity(Integer scId, Integer tagId) {
        this.scId = scId;
        this.tagId = tagId;
    }

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * sc_id
     */
    @ApiModelProperty("sc_id")
    private Integer scId;


    /**
     * tag_id
     */
    @ApiModelProperty("tag_id")
    private Integer tagId;


}
