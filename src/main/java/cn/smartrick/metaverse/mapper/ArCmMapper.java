package cn.smartrick.metaverse.mapper;

import cn.smartrick.metaverse.domain.entity.ArCmEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * [ 文章评论关联表 ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c)  SmartRickInc. All rights reserved.
 * @date 2022-08-27 16:20:10
 * @since JDK1.8
 */
@Mapper
@Component
public interface ArCmMapper extends BaseMapper<ArCmEntity> {

}
