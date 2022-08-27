package cn.smartrick.metaverse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.smartrick.metaverse.domain.dto.query.CommentQueryDTO;
import cn.smartrick.metaverse.domain.entity.CommentEntity;
import cn.smartrick.metaverse.domain.vo.CommentVO;
import cn.smartrick.metaverse.domain.vo.excel.CommentExcelVO;
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
}
