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
import cn.smartrick.metaverse.domain.entity.TagEntity;
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
import java.util.stream.Collectors;

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
        for (ShucangPlatformVO record : voList.getRecords()) {
            record.setBlockchainList(blockchainMapper.selectListByScId(record.getId()));
            record.setTagList(tagMapper.selectListByScId(record.getId()).stream().filter(item -> item.getTagType() == TagEntity.TAG_TYPE_CLIENT).collect(Collectors.toList()));
        }
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
        this.storeTags(entity.getId(), addDTO.getTagIds());
        //Blockchain存储
        this.storeBlockchains(entity.getId(), addDTO.getBlockchainIds());

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
        this.storeTags(entity.getId(), updateDTO.getTagIds());
        //Blockchain存储
        this.storeBlockchains(entity.getId(), updateDTO.getBlockchainIds());

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
        if (shucangPlatformMapper.deleteById(id) < 1) {
            throw new BusinessException(ResponseCode.DATA_REMOVE_FAIL);
        }
        //删除关联信息，以及关联的TAG表数据
        scBcMapper.delete(new LambdaQueryWrapper<ScBcEntity>().eq(ScBcEntity::getScId, id));

        LambdaQueryWrapper<ScTagEntity> scTagEntityByScId = new LambdaQueryWrapper<ScTagEntity>().eq(ScTagEntity::getScId, id);
        List<ScTagEntity> scTagEntities = scTagMapper.selectList(scTagEntityByScId);
        tagMapper.deleteBatchIds(scTagEntities.stream().map(ScTagEntity::getId).collect(Collectors.toList()));
        scTagMapper.delete(scTagEntityByScId);
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

    @Override
    public ResponseDTO<String> contribute(ShucangPlatformAddDTO addDTO) {
        ShucangPlatformEntity entity = SmartBeanUtil.copy(addDTO, ShucangPlatformEntity.class);
        entity.setReviewed(false);
        if (shucangPlatformMapper.insert(entity) != 1) {
            throw new BusinessException(ResponseCode.DATA_INSERT_FAIL);
        }
        //Tag存储
        this.storeTags(entity.getId(), addDTO.getTagIds());
        //Blockchain存储
        this.storeBlockchains(entity.getId(), addDTO.getBlockchainIds());

        return ResponseDTO.succ();
    }

    @Override
    public ResponseDTO<String> reviewPlatform(Integer scId, boolean isPass) {
        if (isPass) {
            ShucangPlatformEntity shucangPlatformEntity = shucangPlatformMapper.selectById(scId);
            if (shucangPlatformEntity == null) {
                throw new BusinessException(ResponseCode.DATA_NOT_EXIST);
            }
            shucangPlatformEntity.setReviewed(true);
            shucangPlatformMapper.updateById(shucangPlatformEntity);
        } else {
            shucangPlatformMapper.deleteById(scId);
        }
        return ResponseDTO.succ();
    }


    /**
     * Tag存储
     *
     * @param scId
     * @param tagIds
     */
    private void storeTags(Integer scId, List<Integer> tagIds) {
        if (CollectionUtil.isNotEmpty(tagIds)) {
            scTagMapper.delete(new LambdaQueryWrapper<ScTagEntity>().eq(ScTagEntity::getScId, scId));
            for (Integer tagId : tagIds) {
                if (tagMapper.selectById(tagId) == null) throw new BusinessException("标签ID：" + tagId + "不存在");
                scTagMapper.insert(new ScTagEntity(scId, tagId));
            }
        }
    }

    /**
     * Blockchain存储
     *
     * @param scId
     * @param blockchainIds
     */
    private void storeBlockchains(Integer scId, List<Integer> blockchainIds) {
        if (CollectionUtil.isNotEmpty(blockchainIds)) {
            scBcMapper.delete(new LambdaQueryWrapper<ScBcEntity>().eq(ScBcEntity::getScId, scId));
            for (Integer blockchainId : blockchainIds) {
                if (blockchainMapper.selectById(blockchainId) == null)
                    throw new BusinessException("区块链ID：" + blockchainId + "不存在");
                scBcMapper.insert(new ScBcEntity(scId, blockchainId));
            }
        }
    }
}
