package cn.smartrick.metaverse.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.mapper.ArticleMapper;
import cn.smartrick.metaverse.domain.dto.add.ArticleAddDTO;
import cn.smartrick.metaverse.domain.dto.update.ArticleUpdateDTO;
import cn.smartrick.metaverse.domain.dto.query.ArticleQueryDTO;
import cn.smartrick.metaverse.domain.entity.ArticleEntity;
import cn.smartrick.metaverse.domain.vo.ArticleVO;
import cn.smartrick.metaverse.domain.vo.excel.ArticleExcelVO;
import cn.smartrick.metaverse.service.ArticleService;
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
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 根据id查询
     */
    @Override
    public ArticleVO queryById(Long id){
        return  SmartBeanUtil.copy(articleMapper.selectById(id), ArticleVO.class);
    }

    /**
     * 分页查询
     * @author SmartRick
     */
    @Override
    public ResponseDTO<PageResultDTO<ArticleVO>> queryByPage(ArticleQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ArticleVO> voList = articleMapper.selectByPage(page, queryDTO);
        PageResultDTO<ArticleVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author SmartRick
     */
    @Override
    public ResponseDTO<String> add(ArticleAddDTO addDTO) {
        ArticleEntity entity = SmartBeanUtil.copy(addDTO, ArticleEntity.class);
        articleMapper.insert(entity);
        return ResponseDTO.succ();
    }

    /**
     * 编辑
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> modify(ArticleUpdateDTO updateDTO) {
        ArticleEntity entity = SmartBeanUtil.copy(updateDTO, ArticleEntity.class);
        articleMapper.updateById(entity);
        return ResponseDTO.succ();
    }

    /**
     * 删除
     * @author SmartRick
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> remove(Long id) {
        articleMapper.deleteById(id);
        return ResponseDTO.succ();
    }

    /**
     * 批量删除
     * @author SmartRick
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> removeByIds(List<Long> idList) {
        QueryWrapper<ArticleEntity> qw = new QueryWrapper<>();
        qw.in("id", idList);
        articleMapper.delete(qw);
        return ResponseDTO.succ();
    }

    /**
     * 查询全部导出对象
     * @author SmartRick
     */
    @Override
    public List<ArticleExcelVO> queryAllExportData(ArticleQueryDTO queryDTO) {
        return articleMapper.selectAllExportData(queryDTO);
    }

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    @Override
    public List<ArticleExcelVO> queryBatchExportData(List<Long> idList) {
        return articleMapper.selectBatchExportData(idList);
    }
}
