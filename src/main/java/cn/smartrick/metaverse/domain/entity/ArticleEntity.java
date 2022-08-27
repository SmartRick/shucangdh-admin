package cn.smartrick.metaverse.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.smartrick.metaverse.common.domain.BaseEntity;
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
@TableName("t_article")
public class ArticleEntity extends BaseEntity implements Serializable{

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    /**
     * article_title
     */
    @ApiModelProperty("article_title")
    private String articleTitle;


    /**
     * cover_img
     */
    @ApiModelProperty("cover_img")
    private String coverImg;


    /**
     * like_num
     */
    @ApiModelProperty("like_num")
    private Integer likeNum;


    /**
     * browse_num
     */
    @ApiModelProperty("browse_num")
    private Integer browseNum;


    /**
     * markdown文章内容
     */
    @ApiModelProperty("markdown文章内容")
    private String content;


}
