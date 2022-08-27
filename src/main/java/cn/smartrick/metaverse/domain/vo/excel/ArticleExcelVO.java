package cn.smartrick.metaverse.domain.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
public class ArticleExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "article_title")
    private String articleTitle;

    @Excel(name = "cover_img")
    private String coverImg;

    @Excel(name = "like_num")
    private Integer likeNum;

    @Excel(name = "browse_num")
    private Integer browseNum;

    @Excel(name = "created_at", format = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @Excel(name = "updated_at", format = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    @Excel(name = "markdown文章内容")
    private String content;



}
