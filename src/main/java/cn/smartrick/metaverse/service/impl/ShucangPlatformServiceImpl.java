package cn.smartrick.metaverse.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.smartrick.metaverse.common.constant.ResponseCode;
import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.domain.dto.add.ShucangPlatformAddDTO;
import cn.smartrick.metaverse.domain.dto.add.TagAddDTO;
import cn.smartrick.metaverse.domain.dto.query.ShucangPlatformQueryDTO;
import cn.smartrick.metaverse.domain.dto.update.ShucangPlatformUpdateDTO;
import cn.smartrick.metaverse.domain.entity.ScBcEntity;
import cn.smartrick.metaverse.domain.entity.ScTagEntity;
import cn.smartrick.metaverse.domain.entity.ShucangPlatformEntity;
import cn.smartrick.metaverse.domain.entity.TagEntity;
import cn.smartrick.metaverse.domain.vo.BlockchainVO;
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
     * ??????id??????
     */
    @Override
    public ShucangPlatformVO queryById(Long id) {
        return SmartBeanUtil.copy(shucangPlatformMapper.selectById(id), ShucangPlatformVO.class);
    }

    /**
     * ????????????
     *
     * @author SmartRick
     */
    @Override
    public ResponseDTO<PageResultDTO<ShucangPlatformVO>> queryByPage(ShucangPlatformQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ShucangPlatformVO> voList = shucangPlatformMapper.selectByPage(page, queryDTO);
        for (ShucangPlatformVO record : voList.getRecords()) {
            record.setBlockchainList(blockchainMapper.selectListByScId(record.getId()).stream().map(BlockchainVO::getId).collect(Collectors.toList()));
            //?????????????????????????????????tag????????????
            record.setTagList(tagMapper.selectListByScId(record.getId())
                    .stream()
                    .filter(item -> queryDTO.getTagTypes().stream().anyMatch(type -> item.getTagType().equals(type)))
                    .collect(Collectors.toList()));
        }
        PageResultDTO<ShucangPlatformVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * ??????
     *
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(ShucangPlatformAddDTO addDTO) {
        ShucangPlatformEntity entity = SmartBeanUtil.copy(addDTO, ShucangPlatformEntity.class);
        entity.setMarketModel(Integer.parseInt(addDTO.getMarketModel()));
        if (shucangPlatformMapper.insert(entity) != 1) {
            throw new BusinessException(ResponseCode.DATA_INSERT_FAIL);
        }
        //Tag??????
        this.storeTags(entity.getId(), addDTO.getTagList());
        //Blockchain??????
        this.storeBlockchains(entity.getId(), addDTO.getBlockchainList());

        return ResponseDTO.succ();
    }

    /**
     * ??????
     *
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> modify(ShucangPlatformUpdateDTO updateDTO) {
        ShucangPlatformEntity entity = SmartBeanUtil.copy(updateDTO, ShucangPlatformEntity.class);
        if (StrUtil.isNotBlank(updateDTO.getMarketModel())) {
            entity.setMarketModel(Integer.parseInt(updateDTO.getMarketModel()));
        }
        if (shucangPlatformMapper.updateById(entity) != 1) {
            throw new BusinessException(ResponseCode.DATA_UPDATE_FAIL);
        }
        //Tag??????
        this.storeTags(entity.getId(), updateDTO.getTagList());
        //Blockchain??????
        this.storeBlockchains(entity.getId(), updateDTO.getBlockchainList());

        return ResponseDTO.succ();
    }


    /**
     * ??????
     *
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> remove(Long id) {
        if (shucangPlatformMapper.deleteById(id) < 1) {
            throw new BusinessException(ResponseCode.DATA_REMOVE_FAIL);
        }
        //????????????????????????????????????TAG?????????
        scBcMapper.delete(new LambdaQueryWrapper<ScBcEntity>().eq(ScBcEntity::getScId, id));

        LambdaQueryWrapper<ScTagEntity> scTagEntityByScId = new LambdaQueryWrapper<ScTagEntity>().eq(ScTagEntity::getScId, id);
        List<ScTagEntity> scTagEntities = scTagMapper.selectList(scTagEntityByScId);
        tagMapper.deleteBatchIds(scTagEntities.stream().map(ScTagEntity::getId).collect(Collectors.toList()));
        scTagMapper.delete(scTagEntityByScId);
        return ResponseDTO.succ();
    }

    /**
     * ????????????
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
     * ????????????????????????
     *
     * @author SmartRick
     */
    @Override
    public List<ShucangPlatformExcelVO> queryAllExportData(ShucangPlatformQueryDTO queryDTO) {
        return shucangPlatformMapper.selectAllExportData(queryDTO);
    }

    /**
     * ????????????????????????
     *
     * @author SmartRick
     */
    @Override
    public List<ShucangPlatformExcelVO> queryBatchExportData(List<Long> idList) {
        return shucangPlatformMapper.selectBatchExportData(idList);
    }

    /**
     * ??????????????????
     *
     * @author SmartRick
     */
    @Override
    public ResponseDTO like(Integer scId) {
        ShucangPlatformEntity shucangPlatformEntity = shucangPlatformMapper.selectById(scId);
        if (shucangPlatformEntity == null) {
            return ResponseDTO.wrap(ResponseCode.DATA_NOT_EXIST);
        }
        shucangPlatformEntity.setLikeNum(shucangPlatformEntity.getLikeNum() + 1);
        shucangPlatformMapper.updateById(shucangPlatformEntity);
        return ResponseDTO.succ();
    }

    /**
     * ??????????????????
     *
     * @author SmartRick
     */
    @Override
    public ResponseDTO browse(Integer scId) {
        ShucangPlatformEntity shucangPlatformEntity = shucangPlatformMapper.selectById(scId);
        if (shucangPlatformEntity == null) {
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
        //Tag??????
        this.storeTags(entity.getId(), addDTO.getTagList());
        //Blockchain??????
        this.storeBlockchains(entity.getId(), addDTO.getBlockchainList());

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
     * Tag??????
     *
     * @param scId
     * @param tagAddDTOS
     */
    private void storeTags(Integer scId, List<TagAddDTO> tagAddDTOS) {
        LambdaQueryWrapper<ScTagEntity> scTagQueryByScId = new LambdaQueryWrapper<ScTagEntity>().eq(ScTagEntity::getScId, scId);
        scTagMapper.delete(scTagQueryByScId);
        //?????????????????????????????????????????????
        List<ScTagEntity> scTagEntities = scTagMapper.selectList(scTagQueryByScId);
        if (CollectionUtil.isNotEmpty(scTagEntities)) {
            tagMapper.deleteBatchIds(scTagEntities.stream().map(ScTagEntity::getTagId).collect(Collectors.toList()));
        }

        if (CollectionUtil.isNotEmpty(tagAddDTOS)) {
            //????????????????????????
            for (TagAddDTO tagAddDTO : tagAddDTOS) {
                TagEntity tagEntity = SmartBeanUtil.copy(tagAddDTO, TagEntity.class);
                if (tagMapper.insert(tagEntity) != 1)
                    throw new BusinessException("????????????[" + tagEntity.getTagName() + " : " + tagEntity.getLink() + "]??????");
                scTagMapper.insert(new ScTagEntity(scId, tagEntity.getId()));
            }
        }
    }

    /**
     * Blockchain??????
     *
     * @param scId
     * @param blockchainIds
     */
    private void storeBlockchains(Integer scId, List<Integer> blockchainIds) {
        scBcMapper.delete(new LambdaQueryWrapper<ScBcEntity>().eq(ScBcEntity::getScId, scId));
        if (CollectionUtil.isNotEmpty(blockchainIds)) {
            for (Integer blockchainId : blockchainIds) {
                if (blockchainMapper.selectById(blockchainId) == null)
                    throw new BusinessException("?????????ID???" + blockchainId + "?????????");
                scBcMapper.insert(new ScBcEntity(scId, blockchainId));
            }
        }
    }
}
