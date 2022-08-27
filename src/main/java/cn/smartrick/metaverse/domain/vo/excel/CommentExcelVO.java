package cn.smartrick.metaverse.domain.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.util.Date;

/**
 *  [ 该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户 ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c) SmartRickInc. All rights reserved.
 * @date  2022-08-27 16:20:10
 * @since JDK1.8
 */
@Data
public class CommentExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "评论内容")
    private String content;

    @Excel(name = "用户唯一标识")
    private String userUuid;

    @Excel(name = "created_at", format = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @Excel(name = "updated_at", format = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;



}
