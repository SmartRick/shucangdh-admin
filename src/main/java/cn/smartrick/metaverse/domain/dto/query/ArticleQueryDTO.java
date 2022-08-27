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
public class ArticleQueryDTO extends PageParamDTO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("article_title")
    private String articleTitle;

    @ApiModelProperty("cover_img")
    private String coverImg;

    @ApiModelProperty("like_num")
    private Integer likeNum;

    @ApiModelProperty("browse_num")
    private Integer browseNum;

    @ApiModelProperty("created_at")
    private Date createdAt;

    @ApiModelProperty("updated_at")
    private Date updatedAt;

    @ApiModelProperty("markdown文章内容")
    private String content;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间-截止")
    private Date createTimeEnd;

    @ApiModelProperty("上次更新时间-开始")
    private Date updateTimeBegin;

    @ApiModelProperty("上次更新创建时间-开始")
    private Date updateTimeEnd;
}
