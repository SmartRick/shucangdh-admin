package cn.smartrick.metaverse.mapper;

import cn.smartrick.metaverse.domain.dto.query.CommentQueryDTO;
import cn.smartrick.metaverse.domain.entity.CommentEntity;
import cn.smartrick.metaverse.domain.vo.CommentVO;
import cn.smartrick.metaverse.domain.vo.excel.CommentExcelVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * [ 该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户 ]
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
public interface CommentMapper extends BaseMapper<CommentEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return CommentVO
    */
    IPage<CommentVO> selectByPage(Page page, @Param("queryDTO") CommentQueryDTO queryDTO);

    /**
     * 查询所有导出数据
     * @param queryDTO
     * @return
     */
    List<CommentExcelVO> selectAllExportData(@Param("queryDTO")CommentQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<CommentExcelVO> selectBatchExportData(@Param("idList")List<Long> idList);

    /**
     * 根据距离当天的间隔天数(gap)，查询某天的全部数据
     * 例如查询当天：0；查询昨天-1,异常类推
     * @param gap
     * @return
     */
    public List<CommentVO> selectDayByToday(@Param("gap") int gap);

    /**
     * 根据距离当天的间隔天数(gap)，查询某天和之前的所有数据
     * 例如查询当天以及过去所有：0；查询昨天-1以及过去所有,异常类推
     * @param gap
     * @return
     */
    public List<CommentVO> selectPastDataByGap(@Param("gap") int gap);

    /**
     * 查询过去几天的全部数据
     * 例如查询过去7天，days=7
     * @param days
     * @return
     */
    public List<CommentVO> selectRangeByDays(@Param("days") int days);
}
