package cn.smartrick.metaverse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.smartrick.metaverse.domain.dto.query.ScBcQueryDTO;
import cn.smartrick.metaverse.domain.entity.ScBcEntity;
import cn.smartrick.metaverse.domain.vo.ScBcVO;
import cn.smartrick.metaverse.domain.vo.excel.ScBcExcelVO;
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
 * @date 2022-07-27 10:26:37
 * @since JDK1.8
 */
@Mapper
@Component
public interface ScBcMapper extends BaseMapper<ScBcEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ScBcVO
    */
    IPage<ScBcVO> selectByPage(Page page, @Param("queryDTO") ScBcQueryDTO queryDTO);

    /**
     * 查询所有导出数据
     * @param queryDTO
     * @return
     */
    List<ScBcExcelVO> selectAllExportData(@Param("queryDTO")ScBcQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ScBcExcelVO> selectBatchExportData(@Param("idList")List<Long> idList);
}
