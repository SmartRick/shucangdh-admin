package cn.smartrick.metaverse.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 *  [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c) SmartRickInc. All rights reserved.
 * @date  2022-08-27 16:20:10
 * @since JDK1.8
 */
@Data
public class ArticleVO {

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    @ApiModelProperty("updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedAt;

    @ApiModelProperty("markdown文章内容")
    private String content;


}
