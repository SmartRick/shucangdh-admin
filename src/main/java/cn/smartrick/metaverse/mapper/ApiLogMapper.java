package cn.smartrick.metaverse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.smartrick.metaverse.domain.dto.query.ApiLogQueryDTO;
import cn.smartrick.metaverse.domain.entity.ApiLogEntity;
import cn.smartrick.metaverse.domain.vo.ApiLogVO;
import cn.smartrick.metaverse.domain.vo.excel.ApiLogExcelVO;
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
 * @date 2022-08-27 16:20:09
 * @since JDK1.8
 */
@Mapper
@Component
public interface ApiLogMapper extends BaseMapper<ApiLogEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ApiLogVO
    */
    IPage<ApiLogVO> selectByPage(Page page, @Param("queryDTO") ApiLogQueryDTO queryDTO);

    /**
     * 查询所有导出数据
     * @param queryDTO
     * @return
     */
    List<ApiLogExcelVO> selectAllExportData(@Param("queryDTO")ApiLogQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ApiLogExcelVO> selectBatchExportData(@Param("idList")List<Long> idList);

    public List<ApiLogVO> selectDayByToday(@Param("gap") int gap);

    public List<ApiLogVO> selectRangeByDays(@Param("days") int days);
}
