package cn.smartrick.metaverse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import cn.smartrick.metaverse.service.ScTagService;
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
public class ScTagServiceImpl implements ScTagService{

    @Autowired
    private ScTagMapper scTagMapper;

    /**
     * 根据id查询
     */
    @Override
    public ScTagVO queryById(Long id){
        return  SmartBeanUtil.copy(scTagMapper.selectById(id), ScTagVO.class);
    }

    /**
     * 分页查询
     * @author SmartRick
     */
    @Override
    public ResponseDTO<PageResultDTO<ScTagVO>> queryByPage(ScTagQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ScTagVO> voList = scTagMapper.selectByPage(page, queryDTO);
        PageResultDTO<ScTagVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SmartRick
     */
    @Override
    public ResponseDTO<String> add(ScTagAddDTO addDTO) {
        ScTagEntity entity = SmartBeanUtil.copy(addDTO, ScTagEntity.class);
        scTagMapper.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> modify(ScTagUpdateDTO updateDTO) {
        ScTagEntity entity = SmartBeanUtil.copy(updateDTO, ScTagEntity.class);
        scTagMapper.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SmartRick
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> remove(Long id) {
        scTagMapper.deleteById(id);
        return ResponseDTO.succ();
    }

    /**
     * 批量删除
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> removeByIds(List<Long> idList) {
        QueryWrapper<ScTagEntity> qw = new QueryWrapper<>();
        qw.in("id", idList);
        scTagMapper.delete(qw);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SmartRick
     */
    @Override
    public List<ScTagExcelVO> queryAllExportData(ScTagQueryDTO queryDTO) {
        return scTagMapper.selectAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    @Override
    public List<ScTagExcelVO> queryBatchExportData(List<Long> idList) {
        return scTagMapper.selectBatchExportData(idList);
    }
}
