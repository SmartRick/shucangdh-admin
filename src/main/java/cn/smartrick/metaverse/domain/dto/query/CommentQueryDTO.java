package cn.smartrick.metaverse.domain.dto.query;

import cn.smartrick.metaverse.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.Date;

/**
 * [ 该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户 ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c)  SmartRickInc. All rights reserved.
 * @date 2022-08-27 16:20:10
 * @since JDK1.8
 */
@Data
public class CommentQueryDTO extends PageParamDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("用户唯一标识")
    private String userUuid;

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
