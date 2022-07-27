package cn.smartrick.metaverse.domain.dto.add;

import cn.smartrick.metaverse.common.domain.ValidateGroups;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
public class BlockchainAddDTO {
    @NotBlank(message = "区块链名称不能为空", groups = ValidateGroups.ValidateGroupByAdd.class)
    @ApiModelProperty("blockchain")
    private String blockchain;

    @ApiModelProperty("remark")
    private String remark;

    @ApiModelProperty("link")
    private String link;

    @Pattern(regexp = "^#\\w{6}$", message = "背景颜色值格式错误")
    @ApiModelProperty("backgroundColor")
    private String backgroundColor;


}
