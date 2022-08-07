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
public class BlockchainExcelVO {
    @Excel(name = "id")
    private Integer id;

    @Excel(name = "blockchain")
    private String blockchain;

    @Excel(name = "remark")
    private String remark;

    @Excel(name = "link")
    private String link;

    @Excel(name = "background_color")
    private String backgroundColor;



}
