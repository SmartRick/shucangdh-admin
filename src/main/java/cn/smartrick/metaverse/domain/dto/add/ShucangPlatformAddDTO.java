package cn.smartrick.metaverse.domain.dto.add;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 新建 [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c) 2018 SmartRickInc. All rights reserved.
 * @date 2022-07-27 10:26:37
 * @since JDK1.8
 */
@Data
public class ShucangPlatformAddDTO {
    @NotBlank(message = "数藏平台名称不能为空")
    @ApiModelProperty("name")
    private String name;

    @NotBlank(message = "数藏平台Logo不能为空")
    @ApiModelProperty("cover_img")
    private String coverImg;

    @ApiModelProperty("remark")
    private String remark;

    @ApiModelProperty("market_model")
    private String marketModel;

    @ApiModelProperty(value = "market_model", name = "关联的标签")
    private List<Integer> tagIds;

    @ApiModelProperty(value = "market_model", name = "数藏上链信息")
    private List<Integer> blockchainIds;
}
