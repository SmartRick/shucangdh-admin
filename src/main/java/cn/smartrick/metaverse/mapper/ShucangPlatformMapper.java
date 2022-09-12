package cn.smartrick.metaverse.mapper;

import cn.smartrick.metaverse.domain.dto.query.ShucangPlatformQueryDTO;
import cn.smartrick.metaverse.domain.entity.ShucangPlatformEntity;
import cn.smartrick.metaverse.domain.vo.ShucangPlatformVO;
import cn.smartrick.metaverse.domain.vo.excel.ShucangPlatformExcelVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @date 2022-07-27 10:26:37
 * @since JDK1.8
 */
@Mapper
@Component
public interface ShucangPlatformMapper extends BaseMapper<ShucangPlatformEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return ShucangPlatformVO
    */
    IPage<ShucangPlatformVO> selectByPage(Page page, @Param("queryDTO") ShucangPlatformQueryDTO queryDTO);

    /**
     * 查询所有导出数据
     * @param queryDTO
     * @return
     */
    List<ShucangPlatformExcelVO> selectAllExportData(@Param("queryDTO")ShucangPlatformQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<ShucangPlatformExcelVO> selectBatchExportData(@Param("idList")List<Long> idList);


    /**
     * 根据距离当天的间隔天数(gap)，查询某天的全部数据
     * 例如查询当天：0；查询昨天-1,异常类推
     * @param gap
     * @return
     */
    public List<ShucangPlatformVO> selectDayByToday(@Param("gap") int gap);

    /**
     * 根据距离当天的间隔天数(gap)，查询某天和之前的所有数据
     * 例如查询当天以及过去所有：0；查询昨天-1以及过去所有,异常类推
     * @param gap
     * @return
     */
    public List<ShucangPlatformVO> selectPastDataByGap(@Param("gap") int gap);

    /**
     * 查询过去几天的全部数据
     * 例如查询过去7天，days=7
     * @param days
     * @return
     */
    public List<ShucangPlatformVO> selectRangeByDays(@Param("days") int days);

}
