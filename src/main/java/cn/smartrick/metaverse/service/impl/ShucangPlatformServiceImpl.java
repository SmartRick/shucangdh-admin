package cn.smartrick.metaverse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.mapper.ShucangPlatformMapper;
import cn.smartrick.metaverse.domain.dto.add.ShucangPlatformAddDTO;
import cn.smartrick.metaverse.domain.dto.update.ShucangPlatformUpdateDTO;
import cn.smartrick.metaverse.domain.dto.query.ShucangPlatformQueryDTO;
import cn.smartrick.metaverse.domain.entity.ShucangPlatformEntity;
import cn.smartrick.metaverse.domain.vo.ShucangPlatformVO;
import cn.smartrick.metaverse.domain.vo.excel.ShucangPlatformExcelVO;
import cn.smartrick.metaverse.service.ShucangPlatformService;
import cn.smartrick.metaverse.utils.SmartPageUtil;
import cn.smartrick.metaverse.utils.SmartBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

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
public class ShucangPlatformServiceImpl implements ShucangPlatformService{

    @Autowired
    private ShucangPlatformMapper shucangPlatformMapper;

    /**
     * 根据id查询
     */
    @Override
    public ShucangPlatformVO queryById(Long id){
        return  SmartBeanUtil.copy(shucangPlatformMapper.selectById(id), ShucangPlatformVO.class);
    }

    /**
     * 分页查询
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
     * @author SmartRick
     */
    @Override
    public ResponseDTO<String> add(ShucangPlatformAddDTO addDTO) {
        ShucangPlatformEntity entity = SmartBeanUtil.copy(addDTO, ShucangPlatformEntity.class);
        shucangPlatformMapper.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> modify(ShucangPlatformUpdateDTO updateDTO) {
        ShucangPlatformEntity entity = SmartBeanUtil.copy(updateDTO, ShucangPlatformEntity.class);
        shucangPlatformMapper.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
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
     * @author SmartRick
     */
    @Override
    public List<ShucangPlatformExcelVO> queryAllExportData(ShucangPlatformQueryDTO queryDTO) {
        return shucangPlatformMapper.selectAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    @Override
    public List<ShucangPlatformExcelVO> queryBatchExportData(List<Long> idList) {
        return shucangPlatformMapper.selectBatchExportData(idList);
    }
}
