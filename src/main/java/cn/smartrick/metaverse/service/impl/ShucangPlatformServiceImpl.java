package cn.smartrick.metaverse.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.smartrick.metaverse.common.constant.ResponseCode;
import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.domain.dto.add.ShucangPlatformAddDTO;
import cn.smartrick.metaverse.domain.dto.query.ShucangPlatformQueryDTO;
import cn.smartrick.metaverse.domain.dto.update.ShucangPlatformUpdateDTO;
import cn.smartrick.metaverse.domain.entity.ScBcEntity;
import cn.smartrick.metaverse.domain.entity.ScTagEntity;
import cn.smartrick.metaverse.domain.entity.ShucangPlatformEntity;
import cn.smartrick.metaverse.domain.vo.ShucangPlatformVO;
import cn.smartrick.metaverse.domain.vo.excel.ShucangPlatformExcelVO;
import cn.smartrick.metaverse.exception.BusinessException;
import cn.smartrick.metaverse.mapper.*;
import cn.smartrick.metaverse.service.ShucangPlatformService;
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
public class ShucangPlatformServiceImpl implements ShucangPlatformService {

    @Autowired
    private ShucangPlatformMapper shucangPlatformMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ScTagMapper scTagMapper;
    @Autowired
    private ScBcMapper scBcMapper;
    @Autowired
    private BlockchainMapper blockchainMapper;

    /**
     * 根据id查询
     */
    @Override
    public ShucangPlatformVO queryById(Long id) {
        return SmartBeanUtil.copy(shucangPlatformMapper.selectById(id), ShucangPlatformVO.class);
    }

    /**
     * 分页查询
     *
     * @author SmartRick
     */
    @Override
    public ResponseDTO<PageResultDTO<ShucangPlatformVO>> queryByPage(ShucangPlatformQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ShucangPlatformVO> voList = shucangPlatformMapper.selectByPage(page, queryDTO);
        PageResultDTO<ShucangPlatformVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     *
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(ShucangPlatformAddDTO addDTO) {
        ShucangPlatformEntity entity = SmartBeanUtil.copy(addDTO, ShucangPlatformEntity.class);
        if (shucangPlatformMapper.insert(entity) != 1) {
            throw new BusinessException(ResponseCode.DATA_INSERT_FAIL);
        }
        //Tag存储
        List<Integer> tagIds = addDTO.getTagIds();
        if (CollectionUtil.isNotEmpty(tagIds)) {
            for (Integer tagId : tagIds) {
                if (tagMapper.selectById(tagId) == null) throw new BusinessException("标签ID：" + tagId + "不存在");
                scTagMapper.insert(new ScTagEntity(entity.getId(), tagId));
            }
        }
        //Blockchain存储
        List<Integer> blockchainIds = addDTO.getBlockchainIds();
        if (CollectionUtil.isNotEmpty(blockchainIds)) {
            for (Integer blockchainId : blockchainIds) {
                if (blockchainMapper.selectById(blockchainId) == null)
                    throw new BusinessException("区块链ID：" + blockchainId + "不存在");
                scBcMapper.insert(new ScBcEntity(entity.getId(), blockchainId));
            }
        }

        return ResponseDTO.succ();
    }

    /**
     * 编辑
     *
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> modify(ShucangPlatformUpdateDTO updateDTO) {
        ShucangPlatformEntity entity = SmartBeanUtil.copy(updateDTO, ShucangPlatformEntity.class);
        if (shucangPlatformMapper.updateById(entity) != 1) {
            throw new BusinessException(ResponseCode.DATA_UPDATE_FAIL);
        }

        //Tag存储
        List<Integer> tagIds = updateDTO.getTagIds();
        if (CollectionUtil.isNotEmpty(tagIds)) {
            scTagMapper.delete(new LambdaQueryWrapper<ScTagEntity>().eq(ScTagEntity::getScId, updateDTO.getId()));
            for (Integer tagId : tagIds) {
                if (tagMapper.selectById(tagId) == null) throw new BusinessException("标签ID：" + tagId + "不存在");
                scTagMapper.insert(new ScTagEntity(entity.getId(), tagId));
            }
        }
        //Blockchain存储
        List<Integer> blockchainIds = updateDTO.getBlockchainIds();
        if (CollectionUtil.isNotEmpty(blockchainIds)) {
            scBcMapper.delete(new LambdaQueryWrapper<ScBcEntity>().eq(ScBcEntity::getScId, updateDTO.getId()));
            for (Integer blockchainId : blockchainIds) {
                if (blockchainMapper.selectById(blockchainId) == null)
                    throw new BusinessException("区块链ID：" + blockchainId + "不存在");
                scBcMapper.insert(new ScBcEntity(entity.getId(), blockchainId));
            }
        }
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
        shucangPlatformMapper.deleteById(id);
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
        QueryWrapper<ShucangPlatformEntity> qw = new QueryWrapper<>();
        qw.in("id", idList);
        shucangPlatformMapper.delete(qw);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     *
     * @author SmartRick
     */
    @Override
    public List<ShucangPlatformExcelVO> queryAllExportData(ShucangPlatformQueryDTO queryDTO) {
        return shucangPlatformMapper.selectAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     *
     * @author SmartRick
     */
    @Override
    public List<ShucangPlatformExcelVO> queryBatchExportData(List<Long> idList) {
        return shucangPlatformMapper.selectBatchExportData(idList);
    }

    /**
     * 点赞数藏平台
     *
     * @author SmartRick
     */
    @Override
    public ResponseDTO like(Integer scId) {
        ShucangPlatformEntity shucangPlatformEntity = shucangPlatformMapper.selectById(scId);
        if (shucangPlatformEntity != null) {
            return ResponseDTO.wrap(ResponseCode.DATA_NOT_EXIST);
        }
        shucangPlatformEntity.setLickNum(shucangPlatformEntity.getLickNum() + 1);
        shucangPlatformMapper.updateById(shucangPlatformEntity);
        return ResponseDTO.succ();
    }

    /**
     * 浏览数藏平台
     *
     * @author SmartRick
     */
    @Override
    public ResponseDTO browse(Integer scId) {
        ShucangPlatformEntity shucangPlatformEntity = shucangPlatformMapper.selectById(scId);
        if (shucangPlatformEntity != null) {
            return ResponseDTO.wrap(ResponseCode.DATA_NOT_EXIST);
        }
        shucangPlatformEntity.setBrowseNum(shucangPlatformEntity.getBrowseNum() + 1);
        shucangPlatformMapper.updateById(shucangPlatformEntity);
        return ResponseDTO.succ();
    }
}
