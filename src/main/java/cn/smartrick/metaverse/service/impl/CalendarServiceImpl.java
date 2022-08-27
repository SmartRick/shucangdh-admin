package cn.smartrick.metaverse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.mapper.CalendarMapper;
import cn.smartrick.metaverse.domain.dto.add.CalendarAddDTO;
import cn.smartrick.metaverse.domain.dto.update.CalendarUpdateDTO;
import cn.smartrick.metaverse.domain.dto.query.CalendarQueryDTO;
import cn.smartrick.metaverse.domain.entity.CalendarEntity;
import cn.smartrick.metaverse.domain.vo.CalendarVO;
import cn.smartrick.metaverse.domain.vo.excel.CalendarExcelVO;
import cn.smartrick.metaverse.service.CalendarService;
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
 * @date 2022-08-27 16:20:10
 * @since JDK1.8
 */
@Slf4j
@Service
public class CalendarServiceImpl implements CalendarService{

    @Autowired
    private CalendarMapper calendarMapper;

    /**
     * 根据id查询
     */
    @Override
    public CalendarVO queryById(Long id){
        return  SmartBeanUtil.copy(calendarMapper.selectById(id), CalendarVO.class);
    }

    /**
     * 分页查询
     * @author SmartRick
     */
    @Override
    public ResponseDTO<PageResultDTO<CalendarVO>> queryByPage(CalendarQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<CalendarVO> voList = calendarMapper.selectByPage(page, queryDTO);
        PageResultDTO<CalendarVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SmartRick
     */
    @Override
    public ResponseDTO<String> add(CalendarAddDTO addDTO) {
        CalendarEntity entity = SmartBeanUtil.copy(addDTO, CalendarEntity.class);
        calendarMapper.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> modify(CalendarUpdateDTO updateDTO) {
        CalendarEntity entity = SmartBeanUtil.copy(updateDTO, CalendarEntity.class);
        calendarMapper.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SmartRick
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> remove(Long id) {
        calendarMapper.deleteById(id);
        return ResponseDTO.succ();
    }

    /**
     * 批量删除
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> removeByIds(List<Long> idList) {
        QueryWrapper<CalendarEntity> qw = new QueryWrapper<>();
        qw.in("id", idList);
        calendarMapper.delete(qw);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SmartRick
     */
    @Override
    public List<CalendarExcelVO> queryAllExportData(CalendarQueryDTO queryDTO) {
        return calendarMapper.selectAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    @Override
    public List<CalendarExcelVO> queryBatchExportData(List<Long> idList) {
        return calendarMapper.selectBatchExportData(idList);
    }
}
