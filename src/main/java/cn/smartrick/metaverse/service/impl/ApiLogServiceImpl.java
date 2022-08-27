package cn.smartrick.metaverse.service.impl;

import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.domain.dto.add.ApiLogAddDTO;
import cn.smartrick.metaverse.domain.dto.query.ApiLogQueryDTO;
import cn.smartrick.metaverse.domain.entity.ApiLogEntity;
import cn.smartrick.metaverse.domain.vo.ApiLogVO;
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

    @Override
    public ResponseDTO queryToday() {
//        shucangPlatformMapper.selectList()
        return null;
    }
}
