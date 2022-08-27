package cn.smartrick.metaverse.domain.dto.add;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class ArticleAddDTO {
    @ApiModelProperty("article_title")
    private String articleTitle;

    @ApiModelProperty("cover_img")
    private String coverImg;

    @ApiModelProperty("like_num")
    private Integer likeNum;

    @ApiModelProperty("browse_num")
    private Integer browseNum;

    @ApiModelProperty("markdown文章内容")
    private String content;


}
