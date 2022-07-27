package cn.smartrick.metaverse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.mapper.TagMapper;
import cn.smartrick.metaverse.domain.dto.add.TagAddDTO;
import cn.smartrick.metaverse.domain.dto.update.TagUpdateDTO;
import cn.smartrick.metaverse.domain.dto.query.TagQueryDTO;
import cn.smartrick.metaverse.domain.entity.TagEntity;
import cn.smartrick.metaverse.domain.vo.TagVO;
import cn.smartrick.metaverse.domain.vo.excel.TagExcelVO;
import cn.smartrick.metaverse.service.TagService;
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
public class TagServiceImpl implements TagService{

    @Autowired
    private TagMapper tagMapper;

    /**
     * 根据id查询
     */
    @Override
    public TagVO queryById(Long id){
        return  SmartBeanUtil.copy(tagMapper.selectById(id), TagVO.class);
    }

    /**
     * 分页查询
     * @author SmartRick
     */
    @Override
    public ResponseDTO<PageResultDTO<TagVO>> queryByPage(TagQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<TagVO> voList = tagMapper.selectByPage(page, queryDTO);
        PageResultDTO<TagVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SmartRick
     */
    @Override
    public ResponseDTO<String> add(TagAddDTO addDTO) {
        TagEntity entity = SmartBeanUtil.copy(addDTO, TagEntity.class);
        tagMapper.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> modify(TagUpdateDTO updateDTO) {
        TagEntity entity = SmartBeanUtil.copy(updateDTO, TagEntity.class);
        tagMapper.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SmartRick
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> remove(Long id) {
        tagMapper.deleteById(id);
        return ResponseDTO.succ();
    }

    /**
     * 批量删除
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> removeByIds(List<Long> idList) {
        QueryWrapper<TagEntity> qw = new QueryWrapper<>();
        qw.in("id", idList);
        tagMapper.delete(qw);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SmartRick
     */
    @Override
    public List<TagExcelVO> queryAllExportData(TagQueryDTO queryDTO) {
        return tagMapper.selectAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    @Override
    public List<TagExcelVO> queryBatchExportData(List<Long> idList) {
        return tagMapper.selectBatchExportData(idList);
    }
}
