package cn.smartrick.metaverse.service.impl;

import cn.smartrick.metaverse.common.constant.ResponseCode;
import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.domain.dto.add.BlockchainAddDTO;
import cn.smartrick.metaverse.domain.dto.query.BlockchainQueryDTO;
import cn.smartrick.metaverse.domain.dto.update.BlockchainUpdateDTO;
import cn.smartrick.metaverse.domain.entity.BlockchainEntity;
import cn.smartrick.metaverse.domain.vo.BlockchainVO;
import cn.smartrick.metaverse.domain.vo.excel.BlockchainExcelVO;
import cn.smartrick.metaverse.mapper.BlockchainMapper;
import cn.smartrick.metaverse.service.BlockchainService;
import cn.smartrick.metaverse.utils.SmartBeanUtil;
import cn.smartrick.metaverse.utils.SmartPageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c)  SmartRickInc. All rights reserved.
 * @date 2022-07-27 10:29:44
 * @since JDK1.8
 */
@Slf4j
@Service
public class BlockchainServiceImpl implements BlockchainService {

    @Autowired
    private BlockchainMapper blockchainMapper;

    /**
     * 根据id查询
     */
    @Override
    public BlockchainVO queryById(Long id) {
        return SmartBeanUtil.copy(blockchainMapper.selectById(id), BlockchainVO.class);
    }

    /**
     * 分页查询
     *
     * @author SmartRick
     */
    @Override
    public ResponseDTO<PageResultDTO<BlockchainVO>> queryByPage(BlockchainQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<BlockchainVO> voList = blockchainMapper.selectByPage(page, queryDTO);
        PageResultDTO<BlockchainVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author SmartRick
     */
    @Override
    public ResponseDTO<String> add(BlockchainAddDTO addDTO) {
        BlockchainEntity entity = SmartBeanUtil.copy(addDTO, BlockchainEntity.class);
        if (blockchainMapper.selectCount(new LambdaQueryWrapper<BlockchainEntity>().eq(BlockchainEntity::getBlockchain, addDTO.getBlockchain())) >= 1) {
            return ResponseDTO.failMsg("区块链名称已经存在");
        }
        blockchainMapper.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     *
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> modify(BlockchainUpdateDTO updateDTO) {
        BlockchainEntity entity = SmartBeanUtil.copy(updateDTO, BlockchainEntity.class);
        blockchainMapper.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     *
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> remove(Long id) {
        blockchainMapper.deleteById(id);
        return ResponseDTO.succ();
    }

    /**
     * 批量删除
     *
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> removeByIds(List<Long> idList) {
        QueryWrapper<BlockchainEntity> qw = new QueryWrapper<>();
        qw.in("id", idList);
        blockchainMapper.delete(qw);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author SmartRick
     */
    @Override
    public List<BlockchainExcelVO> queryAllExportData(BlockchainQueryDTO queryDTO) {
        return blockchainMapper.selectAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author SmartRick
     */
    @Override
    public List<BlockchainExcelVO> queryBatchExportData(List<Long> idList) {
        return blockchainMapper.selectBatchExportData(idList);
    }

}
