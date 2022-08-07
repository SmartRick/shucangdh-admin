package cn.smartrick.metaverse.domain.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 *  [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c) SmartRickInc. All rights reserved.
 * @date  2022-07-27 10:26:37
 * @since JDK1.8
 */
@Data
public class TagExcelVO {
    @Excel(name = "id")
    private Integer id;

    @Excel(name = "tag_name")
    private String tagName;

    @Excel(name = "tag_type")
    private Integer tagType;

    @Excel(name = "link")
    private String link;

    @Excel(name = "background_color")
    private String backgroundColor;



}
