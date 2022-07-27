package cn.smartrick.metaverse.domain.entity;

import cn.smartrick.metaverse.common.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("t_sc_bc")
public class ScBcEntity extends BaseEntity implements Serializable{

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    /**
     * sc_id
     */
    @ApiModelProperty("sc_id")
    private Integer scId;


    /**
     * bc_id
     */
    @ApiModelProperty("bc_id")
    private Integer bcId;


}
