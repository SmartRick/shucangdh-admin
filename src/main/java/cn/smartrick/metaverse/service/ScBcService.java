package cn.smartrick.metaverse.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.mapper.ScBcMapper;
import cn.smartrick.metaverse.domain.dto.add.ScBcAddDTO;
import cn.smartrick.metaverse.domain.dto.update.ScBcUpdateDTO;
import cn.smartrick.metaverse.domain.dto.query.ScBcQueryDTO;
import cn.smartrick.metaverse.domain.entity.ScBcEntity;
import cn.smartrick.metaverse.domain.vo.ScBcVO;
import cn.smartrick.metaverse.domain.vo.excel.ScBcExcelVO;


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
public interface ScBcService {

    /**
     * 根据id查询
     * @author SmartRick
     */
    public ScBcVO queryById(Long id);

    /**
     * 分页查询
     * @author SmartRick
     */
    public ResponseDTO<PageResultDTO<ScBcVO>> queryByPage(ScBcQueryDTO queryDTO);

    /**
     * 添加
     * @author SmartRick
     */
    public ResponseDTO<String> add(ScBcAddDTO addDTO);

    /**
     * 编辑
     * @author SmartRick
     */
    public ResponseDTO<String> modify(ScBcUpdateDTO updateDTO);

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
    public List<ScBcExcelVO> queryAllExportData(ScBcQueryDTO queryDTO);

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    public List<ScBcExcelVO> queryBatchExportData(List<Long> idList);
}
