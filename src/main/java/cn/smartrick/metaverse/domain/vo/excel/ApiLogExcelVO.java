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
 * @date  2022-08-27 16:20:09
 * @since JDK1.8
 */
@Data
public class ApiLogExcelVO {
    @Excel(name = "id")
    private Long id;

    @Excel(name = "module")
    private String module;

    @Excel(name = "api_name")
    private String apiName;

    @Excel(name = "api_path")
    private String apiPath;

    @Excel(name = "client_uuid")
    private String clientUuid;

    @Excel(name = "remote_addr")
    private String remoteAddr;

    @Excel(name = "platform")
    private String platform;

    @Excel(name = "req_time", format = "yyyy-MM-dd HH:mm:ss")
    private Date reqTime;



}
