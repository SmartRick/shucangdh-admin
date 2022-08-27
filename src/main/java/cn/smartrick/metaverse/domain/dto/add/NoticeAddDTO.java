package cn.smartrick.metaverse.domain.dto.add;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c) 2018 SmartRickInc. All rights reserved.
 * @date  2022-08-27 16:20:10
 * @since JDK1.8
 */
@Data
public class NoticeAddDTO {
    @ApiModelProperty("notice_title")
    private String noticeTitle;

    @ApiModelProperty("markdown公告内容")
    private String content;

    @ApiModelProperty("通知类型，1：公告栏通知(详细)，2：大喇叭通知(简略标题)")
    private Integer noticeType;

    @ApiModelProperty("通知开始时间，默认从添加开始")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty("通知结束时间，默认空永久通知")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;


}
