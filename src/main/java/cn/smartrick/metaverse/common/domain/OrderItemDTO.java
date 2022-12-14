package cn.smartrick.metaverse.common.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: zhuoda
 * @create: 2020-03-20 09:07 PM from win10
 */

@Slf4j
@Data
@Accessors(chain = true)
public class OrderItemDTO {
    private String column;
    private boolean asc = true;
}
