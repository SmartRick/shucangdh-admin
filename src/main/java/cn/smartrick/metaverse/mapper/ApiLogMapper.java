package cn.smartrick.metaverse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.smartrick.metaverse.domain.dto.query.ApiLogQueryDTO;
import cn.smartrick.metaverse.domain.entity.ApiLogEntity;
import cn.smartrick.metaverse.domain.vo.ApiLogVO;
import cn.smartrick.metaverse.domain.vo.excel.ApiLogExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

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
@Mapper
@Component
public interface ApiLogMapper extends BaseMapper<ApiLogEntity> {

    /**
     * 分页查询
     *
     * @param queryDTO
     * @return ApiLogVO
     */
    IPage<ApiLogVO> selectByPage(Page page, @Param("queryDTO") ApiLogQueryDTO queryDTO);

    /**
     * 查询所有导出数据
     *
     * @param queryDTO
     * @return
     */
    List<ApiLogExcelVO> selectAllExportData(@Param("queryDTO") ApiLogQueryDTO queryDTO);

    /**
     * 查询批量导出数据
     *
     * @param idList
     * @return
     */
    List<ApiLogExcelVO> selectBatchExportData(@Param("idList") List<Long> idList);

    /**
     * 根据距离当天的间隔天数(gap)，查询某天的全部数据
     * 例如查询当天：0；查询昨天-1,异常类推
     *
     * @param gap
     * @return
     */
    public List<ApiLogVO> selectDayByToday(@Param("gap") Integer gap);

    /**
     * 查询过去几天的全部数据
     * 例如查询过去7天，days=7
     *
     * @param days
     * @return
     */
    public List<ApiLogVO> selectRangeByDays(@Param("days") Integer days);

    /**
     * 根据请求日志信息判定新增客户端指纹，也就是新增用户数量
     *
     * 两个参数二选一
     * @param gap   间隔第几天的数据
     * @param days  最近几天的数据
     * @return
     */
    public Integer selectUserClientIncrement(@Param("gap") Integer gap,@Param("days") Integer days);

    /**
     * 根据距离今日的间隔天数(gap)，查询接口请求统计数量
     * @param gap       日期间隔
     * @param module    模块名称
     * @param apiName   接口名称
     * @param apiPath   接口路径
     * @return
     */
    public Integer selectDailyCount(@Param("gap") Integer gap, @Param("module") String module, @Param("apiName") String apiName, @Param("apiPath") String apiPath);

    /**
     * 根据最近天数(days)，查询接口请求统计数量
     * @param days      近多少天的数据量
     * @param module    模块名称
     * @param apiName   接口名称
     * @param apiPath   接口路径
     * @return
     */
    public Integer selectRangeCount(@Param("days") Integer days, @Param("module") String module, @Param("apiName") String apiName, @Param("apiPath") String apiPath);
}
