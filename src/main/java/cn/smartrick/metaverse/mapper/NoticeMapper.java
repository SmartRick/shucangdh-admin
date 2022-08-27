package cn.smartrick.metaverse.mapper;

import cn.smartrick.metaverse.domain.dto.query.NoticeQueryDTO;
import cn.smartrick.metaverse.domain.entity.NoticeEntity;
import cn.smartrick.metaverse.domain.vo.NoticeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * [  ]
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
public interface NoticeMapper extends BaseMapper<NoticeEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return NoticeVO
    */
    IPage<NoticeVO> selectByPage(Page page, @Param("queryDTO") NoticeQueryDTO queryDTO);
}
