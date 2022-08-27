package cn.smartrick.metaverse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.mapper.NoticeMapper;
import cn.smartrick.metaverse.domain.dto.add.NoticeAddDTO;
import cn.smartrick.metaverse.domain.dto.update.NoticeUpdateDTO;
import cn.smartrick.metaverse.domain.dto.query.NoticeQueryDTO;
import cn.smartrick.metaverse.domain.entity.NoticeEntity;
import cn.smartrick.metaverse.domain.vo.NoticeVO;
import cn.smartrick.metaverse.service.NoticeService;
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
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    private NoticeMapper noticeMapper;

    /**
     * 根据id查询
     */
    @Override
    public NoticeVO queryById(Long id){
        return  SmartBeanUtil.copy(noticeMapper.selectById(id), NoticeVO.class);
    }

    /**
     * 分页查询
     * @author SmartRick
     */
    @Override
    public ResponseDTO<PageResultDTO<NoticeVO>> queryByPage(NoticeQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<NoticeVO> voList = noticeMapper.selectByPage(page, queryDTO);
        PageResultDTO<NoticeVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SmartRick
     */
    @Override
    public ResponseDTO<String> add(NoticeAddDTO addDTO) {
        NoticeEntity entity = SmartBeanUtil.copy(addDTO, NoticeEntity.class);
        noticeMapper.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> modify(NoticeUpdateDTO updateDTO) {
        NoticeEntity entity = SmartBeanUtil.copy(updateDTO, NoticeEntity.class);
        noticeMapper.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SmartRick
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> remove(Long id) {
        noticeMapper.deleteById(id);
        return ResponseDTO.succ();
    }

    /**
     * 批量删除
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> removeByIds(List<Long> idList) {
        QueryWrapper<NoticeEntity> qw = new QueryWrapper<>();
        qw.in("id", idList);
        noticeMapper.delete(qw);
        return ResponseDTO.succ();
    }

}
