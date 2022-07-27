package cn.smartrick.metaverse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.mapper.ScTagMapper;
import cn.smartrick.metaverse.domain.dto.add.ScTagAddDTO;
import cn.smartrick.metaverse.domain.dto.update.ScTagUpdateDTO;
import cn.smartrick.metaverse.domain.dto.query.ScTagQueryDTO;
import cn.smartrick.metaverse.domain.entity.ScTagEntity;
import cn.smartrick.metaverse.domain.vo.ScTagVO;
import cn.smartrick.metaverse.domain.vo.excel.ScTagExcelVO;


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
public interface ScTagService {

    /**
     * 根据id查询
     * @author SmartRick
     */
    public ScTagVO queryById(Long id);

    /**
     * 分页查询
     * @author SmartRick
     */
    public ResponseDTO<PageResultDTO<ScTagVO>> queryByPage(ScTagQueryDTO queryDTO);

    /**
     * 添加
     * @author SmartRick
     */
    public ResponseDTO<String> add(ScTagAddDTO addDTO);

    /**
     * 编辑
     * @author SmartRick
     */
    public ResponseDTO<String> modify(ScTagUpdateDTO updateDTO);

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
    public List<ScTagExcelVO> queryAllExportData(ScTagQueryDTO queryDTO);

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    public List<ScTagExcelVO> queryBatchExportData(List<Long> idList);
}
