package cn.smartrick.metaverse.domain.dto.add;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 新建 [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c) 2018 SmartRickInc. All rights reserved.
 * @date  2022-08-27 16:20:09
 * @since JDK1.8
 */
@Data
public class ApiLogAddDTO {
    @ApiModelProperty("module")
    private String module;

    @ApiModelProperty("api_name")
    private String apiName;

    @ApiModelProperty("api_path")
    private String apiPath;

    @ApiModelProperty("client_uuid")
    private String clientUuid;

    @ApiModelProperty("remote_addr")
    private String remoteAddr;

    @ApiModelProperty("platform")
    private String platform;

    @ApiModelProperty("req_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date reqTime;


}
