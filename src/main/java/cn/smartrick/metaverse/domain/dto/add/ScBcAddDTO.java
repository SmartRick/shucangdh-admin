package cn.smartrick.metaverse.domain.dto.add;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

/**
 * 新建 [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c) 2018 SmartRickInc. All rights reserved.
 * @date  2022-07-27 10:26:37
 * @since JDK1.8
 */
@Data
public class ScBcAddDTO {
    @ApiModelProperty("sc_id")
    private Long scId;

    @ApiModelProperty("bc_id")
    private Long bcId;


}
