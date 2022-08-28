package cn.smartrick.metaverse.service.impl;

import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.domain.dto.add.ApiLogAddDTO;
import cn.smartrick.metaverse.domain.dto.query.ApiLogQueryDTO;
import cn.smartrick.metaverse.domain.entity.ApiLogEntity;
import cn.smartrick.metaverse.domain.vo.ApiLogVO;
import cn.smartrick.metaverse.domain.vo.CommentVO;
import cn.smartrick.metaverse.domain.vo.DailyDataVo;
import cn.smartrick.metaverse.domain.vo.ShucangPlatformVO;
import cn.smartrick.metaverse.domain.vo.excel.ApiLogExcelVO;
import cn.smartrick.metaverse.mapper.ApiLogMapper;
import cn.smartrick.metaverse.mapper.CommentMapper;
import cn.smartrick.metaverse.mapper.ShucangPlatformMapper;
import cn.smartrick.metaverse.service.ApiLogService;
import cn.smartrick.metaverse.utils.SmartBeanUtil;
import cn.smartrick.metaverse.utils.SmartPageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
@Slf4j
@Service
public class ApiLogServiceImpl implements ApiLogService{

    @Autowired
    private ApiLogMapper apiLogMapper;
    @Autowired
    private ShucangPlatformMapper shucangPlatformMapper;
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 根据id查询
     */
    @Override
    public ApiLogVO queryById(Long id){
        return  SmartBeanUtil.copy(apiLogMapper.selectById(id), ApiLogVO.class);
    }

    /**
     * 分页查询
     * @author SmartRick
     */
    @Override
    public ResponseDTO<PageResultDTO<ApiLogVO>> queryByPage(ApiLogQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ApiLogVO> voList = apiLogMapper.selectByPage(page, queryDTO);
        PageResultDTO<ApiLogVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SmartRick
     */
    @Override
    public ResponseDTO<String> add(ApiLogAddDTO addDTO) {
        ApiLogEntity entity = SmartBeanUtil.copy(addDTO, ApiLogEntity.class);
        apiLogMapper.insert(entity);
        return ResponseDTO.succ();
    }


    /**
     * 删除
     * @author SmartRick
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> remove(Long id) {
        apiLogMapper.deleteById(id);
        return ResponseDTO.succ();
    }

    /**
     * 批量删除
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> removeByIds(List<Long> idList) {
        QueryWrapper<ApiLogEntity> qw = new QueryWrapper<>();
        qw.in("id", idList);
        apiLogMapper.delete(qw);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SmartRick
     */
    @Override
    public List<ApiLogExcelVO> queryAllExportData(ApiLogQueryDTO queryDTO) {
        return apiLogMapper.selectAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    @Override
    public List<ApiLogExcelVO> queryBatchExportData(List<Long> idList) {
        return apiLogMapper.selectBatchExportData(idList);
    }

    /**
     * 查询距离几天的单天数据
     * @param gap
     * @return
     */
    @Override
    public ResponseDTO<DailyDataVo> queryDailyByGap(int gap) {
        DailyDataVo dailyDataVo = queryDailyDataVoByGap(gap);
        return ResponseDTO.succData(dailyDataVo);
    }

    /**
     * 查询最近几天的每天数据
     * @param days
     * @return
     */
    @Override
    public ResponseDTO<List<DailyDataVo>> queryRecentDays(int days) {
        return ResponseDTO.succData(queryRecentDaysDataVoByDays(days));
    }

    /**
     * 查询最近几天的汇总数据
     * @param days
     * @return
     */
    @Override
    public ResponseDTO<DailyDataVo> queryRecentDaySum(int days) {
        List<ShucangPlatformVO> shucangPlatformVOS = shucangPlatformMapper.selectRangeByDays(days);
        List<CommentVO> commentVOS = commentMapper.selectRangeByDays(days);
        Integer apiReqCount = apiLogMapper.selectRangeCount(days, null, null, null);
        Integer likeCount = apiLogMapper.selectRangeCount(days, "数藏平台", "点赞数藏平台", null);
        Integer browseCount = apiLogMapper.selectRangeCount(days, "数藏平台", "浏览数藏平台", null);
        Integer newUserCount = apiLogMapper.selectUserClientIncrement(null,days);

        DailyDataVo dailyDataVo = new DailyDataVo();
        dailyDataVo.setClick(apiReqCount);
        dailyDataVo.setBrowse(browseCount);
        dailyDataVo.setLike(likeCount);
        dailyDataVo.setNewUser(newUserCount);
        dailyDataVo.setComment(commentVOS.size());
        dailyDataVo.setNewPlatform(shucangPlatformVOS.size());
        return ResponseDTO.succData(dailyDataVo);
    }

    private List<DailyDataVo> queryRecentDaysDataVoByDays(int days){
        ArrayList<DailyDataVo> dailyDataVos = new ArrayList<>();
        for (int i = days; i > 0; i--) {
            dailyDataVos.add(queryDailyDataVoByGap(-i));
        }
        return dailyDataVos;
    }


    private DailyDataVo queryDailyDataVoByGap(int gap){
        List<ShucangPlatformVO> shucangPlatformVOS = shucangPlatformMapper.selectDayByToday(gap);
        List<CommentVO> commentVOS = commentMapper.selectDayByToday(gap);
        Integer dailyApiReqCount = apiLogMapper.selectDailyCount(gap, null, null, null);
        Integer dailyLikeCount = apiLogMapper.selectDailyCount(gap, "数藏平台", "点赞数藏平台", null);
        Integer dailyBrowseCount = apiLogMapper.selectDailyCount(gap, "数藏平台", "浏览数藏平台", null);
        Integer dailyNewUserCount = apiLogMapper.selectUserClientIncrement(gap,null);

        DailyDataVo dailyDataVo = new DailyDataVo();
        dailyDataVo.setClick(dailyApiReqCount);
        dailyDataVo.setBrowse(dailyBrowseCount);
        dailyDataVo.setLike(dailyLikeCount);
        dailyDataVo.setNewUser(dailyNewUserCount);
        dailyDataVo.setComment(commentVOS.size());
        dailyDataVo.setNewPlatform(shucangPlatformVOS.size());
        return dailyDataVo;
    }
}
