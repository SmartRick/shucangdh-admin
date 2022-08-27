package cn.smartrick.metaverse.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.smartrick.metaverse.common.domain.BaseEntity;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c)  SmartRickInc. All rights reserved.
 * @date 2022-08-27 16:20:10
 * @since JDK1.8
 */
@Data
@TableName("t_notice")
public class NoticeEntity extends BaseEntity implements Serializable{

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    /**
     * notice_title
     */
    @ApiModelProperty("notice_title")
    private String noticeTitle;


    /**
     * markdown公告内容
     */
    @ApiModelProperty("markdown公告内容")
    private String content;


    /**
     * 通知类型，1：公告栏通知(详细)，2：大喇叭通知(简略标题)
     */
    @ApiModelProperty("通知类型，1：公告栏通知(详细)，2：大喇叭通知(简略标题)")
    private Integer noticeType;


    /**
     * 通知开始时间，默认从添加开始
     */
    @ApiModelProperty("通知开始时间，默认从添加开始")
    private Date startTime;


    /**
     * 通知结束时间，默认空永久通知
     */
    @ApiModelProperty("通知结束时间，默认空永久通知")
    private Date endTime;


}
