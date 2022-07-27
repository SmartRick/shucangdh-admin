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
public class ScBcExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "sc_id")
    private Long scId;

    @Excel(name = "bc_id")
    private Long bcId;



}
