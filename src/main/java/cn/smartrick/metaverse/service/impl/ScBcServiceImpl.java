package cn.smartrick.metaverse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import cn.smartrick.metaverse.service.ScBcService;
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
public class ScBcServiceImpl implements ScBcService{

    @Autowired
    private ScBcMapper scBcMapper;

    /**
     * 根据id查询
     */
    @Override
    public ScBcVO queryById(Long id){
        return  SmartBeanUtil.copy(scBcMapper.selectById(id), ScBcVO.class);
    }

    /**
     * 分页查询
     * @author SmartRick
     */
    @Override
    public ResponseDTO<PageResultDTO<ScBcVO>> queryByPage(ScBcQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ScBcVO> voList = scBcMapper.selectByPage(page, queryDTO);
        PageResultDTO<ScBcVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SmartRick
     */
    @Override
    public ResponseDTO<String> add(ScBcAddDTO addDTO) {
        ScBcEntity entity = SmartBeanUtil.copy(addDTO, ScBcEntity.class);
        scBcMapper.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> modify(ScBcUpdateDTO updateDTO) {
        ScBcEntity entity = SmartBeanUtil.copy(updateDTO, ScBcEntity.class);
        scBcMapper.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SmartRick
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> remove(Long id) {
        scBcMapper.deleteById(id);
        return ResponseDTO.succ();
    }

    /**
     * 批量删除
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> removeByIds(List<Long> idList) {
        QueryWrapper<ScBcEntity> qw = new QueryWrapper<>();
        qw.in("id", idList);
        scBcMapper.delete(qw);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SmartRick
     */
    @Override
    public List<ScBcExcelVO> queryAllExportData(ScBcQueryDTO queryDTO) {
        return scBcMapper.selectAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    @Override
    public List<ScBcExcelVO> queryBatchExportData(List<Long> idList) {
        return scBcMapper.selectBatchExportData(idList);
    }
}
