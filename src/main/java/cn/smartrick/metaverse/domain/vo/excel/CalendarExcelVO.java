package cn.smartrick.metaverse.domain.vo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import java.util.Date;
import java.math.BigDecimal;

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
public class CalendarExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "藏品名称")
    private String name;

    @Excel(name = "cover_img")
    private String coverImg;

    @Excel(name = "藏品状态：1：待发售，2：发售中，3：发售结束")
    private Integer state;

    @Excel(name = "发布藏品所在的平台ID")
    private Long platfromId;

    @Excel(name = "发售作者")
    private String releaseAuthor;

    @Excel(name = "发售数量")
    private Integer releaseNum;

    @Excel(name = "发售时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;

    @Excel(name = "藏品价格")
    private BigDecimal price;

    @Excel(name = "如果商品的链接为空默认跳转到数藏平台官方地址")
    private String goodsUrl;

    @Excel(name = "created_at", format = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @Excel(name = "updated_at", format = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;



}
