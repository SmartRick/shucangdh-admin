package cn.smartrick.metaverse.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
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
@TableName("t_api_log")
public class ApiLogEntity implements Serializable{

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * module
     */
    @ApiModelProperty("module")
    private String module;


    /**
     * api_name
     */
    @ApiModelProperty("api_name")
    private String apiName;


    /**
     * api_path
     */
    @ApiModelProperty("api_path")
    private String apiPath;


    /**
     * client_uuid
     */
    @ApiModelProperty("client_uuid")
    private String clientUuid;


    /**
     * remote_addr
     */
    @ApiModelProperty("remote_addr")
    private String remoteAddr;


    /**
     * platform
     */
    @ApiModelProperty("platform")
    private String platform;


    /**
     * req_time
     */
    @ApiModelProperty("req_time")
    private Date reqTime;

}
