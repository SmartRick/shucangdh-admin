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
public class ShucangPlatformExcelVO {
    @Excel(name = "id")
    private Integer id;

    @Excel(name = "name")
    private String name;

    @Excel(name = "cover_img")
    private String coverImg;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "market_model")
    private String marketModel;

    @Excel(name = "browse_num")
    private Integer browseNum;

    @Excel(name = "lick_num")
    private Integer lickNum;



}
