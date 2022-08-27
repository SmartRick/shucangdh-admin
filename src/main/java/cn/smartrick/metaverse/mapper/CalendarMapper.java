package cn.smartrick.metaverse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.smartrick.metaverse.domain.dto.query.CalendarQueryDTO;
import cn.smartrick.metaverse.domain.entity.CalendarEntity;
import cn.smartrick.metaverse.domain.vo.CalendarVO;
import cn.smartrick.metaverse.domain.vo.excel.CalendarExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

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
public interface CalendarMapper extends BaseMapper<CalendarEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return CalendarVO
    */
    IPage<CalendarVO> selectByPage(Page page, @Param("queryDTO") CalendarQueryDTO queryDTO);

    /**
     * 查询所有导出数据
     * @param queryDTO
     * @return
     */
    List<CalendarExcelVO> selectAllExportData(@Param("queryDTO")CalendarQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<CalendarExcelVO> selectBatchExportData(@Param("idList")List<Long> idList);
}
