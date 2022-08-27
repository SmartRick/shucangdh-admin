package cn.smartrick.metaverse.service.impl;

import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.domain.dto.add.CommentAddDTO;
import cn.smartrick.metaverse.domain.dto.query.CommentQueryDTO;
import cn.smartrick.metaverse.domain.entity.CommentEntity;
import cn.smartrick.metaverse.domain.vo.CommentVO;
import cn.smartrick.metaverse.domain.vo.excel.CommentExcelVO;
import cn.smartrick.metaverse.mapper.CommentMapper;
import cn.smartrick.metaverse.service.CommentService;
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
 * [ 该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户 ]
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
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 根据id查询
     */
    @Override
    public CommentVO queryById(Long id){
        return  SmartBeanUtil.copy(commentMapper.selectById(id), CommentVO.class);
    }

    /**
     * 分页查询
     * @author SmartRick
     */
    @Override
    public ResponseDTO<PageResultDTO<CommentVO>> queryByPage(CommentQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<CommentVO> voList = commentMapper.selectByPage(page, queryDTO);
        PageResultDTO<CommentVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SmartRick
     */
    @Override
    public ResponseDTO<String> add(CommentAddDTO addDTO) {
        CommentEntity entity = SmartBeanUtil.copy(addDTO, CommentEntity.class);
        commentMapper.insert(entity);
        return ResponseDTO.succ();
    }


    /**
     * 删除
     * @author SmartRick
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> remove(Long id) {
        commentMapper.deleteById(id);
        return ResponseDTO.succ();
    }

    /**
     * 批量删除
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> removeByIds(List<Long> idList) {
        QueryWrapper<CommentEntity> qw = new QueryWrapper<>();
        qw.in("id", idList);
        commentMapper.delete(qw);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SmartRick
     */
    @Override
    public List<CommentExcelVO> queryAllExportData(CommentQueryDTO queryDTO) {
        return commentMapper.selectAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    @Override
    public List<CommentExcelVO> queryBatchExportData(List<Long> idList) {
        return commentMapper.selectBatchExportData(idList);
    }
}
