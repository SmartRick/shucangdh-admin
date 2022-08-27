package cn.smartrick.metaverse.domain.dto.query;

import cn.smartrick.metaverse.common.domain.PageParamDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c)  SmartRickInc. All rights reserved.
 * @date 2022-08-27 16:20:09
 * @since JDK1.8
 */
@Data
public class ApiLogQueryDTO extends PageParamDTO {

    @ApiModelProperty("id")
    private Long id;

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

    @ApiModelProperty("请求时间-开始")
    private Date reqTimeBegin;

    @ApiModelProperty("请求时间-结束")
    private Date reqTimeEnd;

}
