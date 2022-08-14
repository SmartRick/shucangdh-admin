package cn.smartrick.metaverse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.smartrick.metaverse.domain.dto.query.TagQueryDTO;
import cn.smartrick.metaverse.domain.entity.TagEntity;
import cn.smartrick.metaverse.domain.vo.TagVO;
import cn.smartrick.metaverse.domain.vo.excel.TagExcelVO;
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
public interface TagMapper extends BaseMapper<TagEntity> {

    /**
     * 分页查询
     *
     * @param queryDTO
     * @return TagVO
     */
    IPage<TagVO> selectByPage(Page page, @Param("queryDTO") TagQueryDTO queryDTO);

    /**
     * 查询所有导出数据
     *
     * @param queryDTO
     * @return
     */
    List<TagExcelVO> selectAllExportData(@Param("queryDTO") TagQueryDTO queryDTO);

    /**
     * 查询批量导出数据
     *
     * @param idList
     * @return
     */
    List<TagExcelVO> selectBatchExportData(@Param("idList") List<Long> idList);


    /**
     * 查询数字藏品ID绑定的Tag
     *
     * @param scId 数字藏品Id
     * @return
     */
    List<TagVO> selectListByScId(@Param("scId") Integer scId);

    /**
     * 根据类型代码查询该类型所有名称（去重）
     *
     * @param typeCode 类型编码
     * @return
     */
    List<TagVO> selectListByType(@Param("typeCode") Integer typeCode);

    List<String> selectTagNamesByType(@Param("typeCode") Integer typeCode);
}
