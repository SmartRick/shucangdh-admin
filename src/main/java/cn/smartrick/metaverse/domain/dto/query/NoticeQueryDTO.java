package cn.smartrick.metaverse.domain.dto.query;

import cn.smartrick.metaverse.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

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
public class NoticeQueryDTO extends PageParamDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("notice_title")
    private String noticeTitle;

    @ApiModelProperty("markdown公告内容")
    private String content;

    @ApiModelProperty("通知类型，1：公告栏通知(详细)，2：大喇叭通知(简略标题)")
    private Integer noticeType;

    @ApiModelProperty("通知开始时间，默认从添加开始")
    private Date startTime;

    @ApiModelProperty("通知结束时间，默认空永久通知")
    private Date endTime;

    @ApiModelProperty("created_at")
    private Date createdAt;

    @ApiModelProperty("updated_at")
    private Date updatedAt;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间-截止")
    private Date createTimeEnd;

    @ApiModelProperty("上次更新时间-开始")
    private Date updateTimeBegin;

    @ApiModelProperty("上次更新创建时间-开始")
    private Date updateTimeEnd;
}
