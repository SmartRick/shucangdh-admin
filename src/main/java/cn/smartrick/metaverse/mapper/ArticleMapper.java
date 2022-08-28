package cn.smartrick.metaverse.mapper;

import cn.smartrick.metaverse.domain.dto.query.ArticleQueryDTO;
import cn.smartrick.metaverse.domain.entity.ArticleEntity;
import cn.smartrick.metaverse.domain.vo.ArticleVO;
import cn.smartrick.metaverse.domain.vo.excel.ArticleExcelVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public interface ArticleMapper extends BaseMapper<ArticleEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ArticleVO
    */
    IPage<ArticleVO> selectByPage(Page page, @Param("queryDTO") ArticleQueryDTO queryDTO);

    /**
     * 查询所有导出数据
     * @param queryDTO
     * @return
     */
    List<ArticleExcelVO> selectAllExportData(@Param("queryDTO")ArticleQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ArticleExcelVO> selectBatchExportData(@Param("idList")List<Long> idList);

    /**
     * 根据距离当天的间隔天数(gap)，查询某天的全部数据
     * 例如查询当天：0；查询昨天-1,异常类推
     * @param gap
     * @return
     */
    public List<ArticleVO> selectDayByToday(@Param("gap") int gap);

    /**
     * 查询过去几天的全部数据
     * 例如查询过去7天，days=7
     * @param days
     * @return
     */
    public List<ArticleVO> selectRangeByDays(@Param("days") int days);
}
