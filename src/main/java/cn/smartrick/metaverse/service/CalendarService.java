package cn.smartrick.metaverse.service;

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
public interface CalendarService {

    /**
     * 根据id查询
     * @author SmartRick
     */
    public CalendarVO queryById(Long id);

    /**
     * 分页查询
     * @author SmartRick
     */
    public ResponseDTO<PageResultDTO<CalendarVO>> queryByPage(CalendarQueryDTO queryDTO);

    /**
     * 添加
     * @author SmartRick
     */
    public ResponseDTO<String> add(CalendarAddDTO addDTO);

    /**
     * 编辑
     * @author SmartRick
     */
    public ResponseDTO<String> modify(CalendarUpdateDTO updateDTO);

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
    public List<CalendarExcelVO> queryAllExportData(CalendarQueryDTO queryDTO);

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    public List<CalendarExcelVO> queryBatchExportData(List<Long> idList);
}
