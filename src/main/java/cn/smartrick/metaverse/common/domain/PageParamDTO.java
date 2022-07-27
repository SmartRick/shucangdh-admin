package cn.smartrick.metaverse.common.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 分页基础参数
 *
 * @author lihaifan
 * @Date Created in 2017/10/28 16:19
 */
@Data
public class PageParamDTO {

    @NotNull(message = "分页参数不能为空")
    @Min(value = 1, message = "最小页码为1")
    @ApiModelProperty(value = "页码(不能为空)", example = "1")
    protected Integer pageNum = 1;

    @NotNull(message = "每页数量不能为空")
    @Max(value = 500, message = "每页最大为500")
    @ApiModelProperty(value = "每页数量(不能为空)", example = "10")
    protected Integer pageSize = 10;

    @ApiModelProperty("是否查询总条数")
    protected Boolean searchCount = true;

    @ApiModelProperty("排序")
    protected List<OrderItemDTO> orders;

    public int obtainPage() {
        return (this.pageNum - 1) * this.pageSize;
    }

    public int obtainSize() {
        return obtainPage() + this.pageSize;
    }
}
