package cn.smartrick.metaverse.service;

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
public interface ArticleService {

    /**
     * 根据id查询
     * @author SmartRick
     */
    public ArticleVO queryById(Long id);

    /**
     * 分页查询
     * @author SmartRick
     */
    public ResponseDTO<PageResultDTO<ArticleVO>> queryByPage(ArticleQueryDTO queryDTO);

    /**
     * 添加
     * @author SmartRick
     */
    public ResponseDTO<String> add(ArticleAddDTO addDTO);

    /**
     * 编辑
     * @author SmartRick
     */
    public ResponseDTO<String> modify(ArticleUpdateDTO updateDTO);

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
    public List<ArticleExcelVO> queryAllExportData(ArticleQueryDTO queryDTO);

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    public List<ArticleExcelVO> queryBatchExportData(List<Long> idList);
}
