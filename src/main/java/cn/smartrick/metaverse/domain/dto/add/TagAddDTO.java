package cn.smartrick.metaverse.domain.dto.add;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class TagAddDTO {
    @ApiModelProperty("tag_name")
    private String tagName;

    @ApiModelProperty("tag_type")
    private Integer tagType;

    @ApiModelProperty("link")
    private String link;

    @Pattern(regexp = "^#\\w{6}$",message = "背景颜色值格式错误")
    @ApiModelProperty("backgroundColor")
    private String backgroundColor;


}
