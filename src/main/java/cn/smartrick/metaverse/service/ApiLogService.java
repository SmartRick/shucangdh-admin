package cn.smartrick.metaverse.service;

import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.domain.dto.add.ApiLogAddDTO;
import cn.smartrick.metaverse.domain.dto.query.ApiLogQueryDTO;
import cn.smartrick.metaverse.domain.vo.ApiLogVO;
import cn.smartrick.metaverse.domain.vo.DailyDataVo;
import cn.smartrick.metaverse.domain.vo.excel.ApiLogExcelVO;

import java.util.List;

/**
 * [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @company SmartRick
 * @copyright (c)  SmartRickInc. All rights reserved.
 * @date 2022-08-27 16:20:09
 * @since JDK1.8
 */
public interface ApiLogService {

    /**
     * 根据id查询
     * @author SmartRick
     */
    public ApiLogVO queryById(Long id);

    /**
     * 分页查询
     * @author SmartRick
     */
    public ResponseDTO<PageResultDTO<ApiLogVO>> queryByPage(ApiLogQueryDTO queryDTO);

    /**
     * 添加
     * @author SmartRick
     */
    public ResponseDTO<String> add(ApiLogAddDTO addDTO);

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
    public List<ApiLogExcelVO> queryAllExportData(ApiLogQueryDTO queryDTO);

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    public List<ApiLogExcelVO> queryBatchExportData(List<Long> idList);

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    ResponseDTO<DailyDataVo> queryDailyByGap(int gap);

    /**
     * 查询最近几天的每天数据
     * @param days
     * @return
     */
    ResponseDTO<List<DailyDataVo>> queryRecentDays(int days);

    /**
     * 查询最近几天的汇总数据
     * @param days
     * @return
     */
    ResponseDTO<DailyDataVo> queryRecentDaySum(int days);
}
