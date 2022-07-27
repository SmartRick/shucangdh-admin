package cn.smartrick.metaverse.service;

import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.domain.dto.add.BlockchainAddDTO;
import cn.smartrick.metaverse.domain.dto.query.BlockchainQueryDTO;
import cn.smartrick.metaverse.domain.dto.update.BlockchainUpdateDTO;
import cn.smartrick.metaverse.domain.vo.BlockchainVO;
import cn.smartrick.metaverse.domain.vo.excel.BlockchainExcelVO;

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
public interface BlockchainService {

    /**
     * 根据id查询
     * @author SmartRick
     */
    public BlockchainVO queryById(Long id);

    /**
     * 分页查询
     * @author SmartRick
     */
    public ResponseDTO<PageResultDTO<BlockchainVO>> queryByPage(BlockchainQueryDTO queryDTO);

    /**
     * 添加
     * @author SmartRick
     */
    public ResponseDTO<String> add(BlockchainAddDTO addDTO);

    /**
     * 编辑
     * @author SmartRick
     */
    public ResponseDTO<String> modify(BlockchainUpdateDTO updateDTO);

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
    public List<BlockchainExcelVO> queryAllExportData(BlockchainQueryDTO queryDTO);

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    public List<BlockchainExcelVO> queryBatchExportData(List<Long> idList);

}
