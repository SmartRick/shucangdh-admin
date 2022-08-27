package cn.smartrick.metaverse.domain.dto.add;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [ 该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户 ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c) 2018 SmartRickInc. All rights reserved.
 * @date  2022-08-27 16:20:10
 * @since JDK1.8
 */
@Data
public class CommentAddDTO {
    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("用户唯一标识")
    private String userUuid;


}
