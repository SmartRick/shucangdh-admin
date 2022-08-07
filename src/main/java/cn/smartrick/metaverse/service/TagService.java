package cn.smartrick.metaverse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.mapper.TagMapper;
import cn.smartrick.metaverse.domain.dto.add.TagAddDTO;
import cn.smartrick.metaverse.domain.dto.update.TagUpdateDTO;
import cn.smartrick.metaverse.domain.dto.query.TagQueryDTO;
import cn.smartrick.metaverse.domain.entity.TagEntity;
import cn.smartrick.metaverse.domain.vo.TagVO;
import cn.smartrick.metaverse.domain.vo.excel.TagExcelVO;


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
public interface TagService {

    /**
     * 根据id查询
     * @author SmartRick
     */
    public TagVO queryById(Long id);

    /**
     * 分页查询
     * @author SmartRick
     */
    public ResponseDTO<PageResultDTO<TagVO>> queryByPage(TagQueryDTO queryDTO);

    /**
     * 添加
     * @author SmartRick
     */
    public ResponseDTO<String> add(TagAddDTO addDTO);

    /**
     * 编辑
     * @author SmartRick
     */
    public ResponseDTO<String> modify(TagUpdateDTO updateDTO);

    /**
     * 删除
     * @author SmartRick
    */
    public ResponseDTO<String> remove(Long id);

    /**
     * 批量删除
     * @author SmartRick
     */
    public ResponseDTO<String> removeByIds(List<Long> idList);

    /**
     * 查询全部导出对象
     * @author SmartRick
     */
    public List<TagExcelVO> queryAllExportData(TagQueryDTO queryDTO);

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    public List<TagExcelVO> queryBatchExportData(List<Long> idList);


    /**
     * 根据类型代码查询该类型所有名称（去重）
     *
     * @param tagType 类型编码
     * @return
     */
    public List<String> queryTagNamesByType(Integer tagType);


}
