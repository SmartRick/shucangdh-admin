package cn.smartrick.metaverse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.smartrick.metaverse.domain.dto.query.BlockchainQueryDTO;
import cn.smartrick.metaverse.domain.entity.BlockchainEntity;
import cn.smartrick.metaverse.domain.vo.BlockchainVO;
import cn.smartrick.metaverse.domain.vo.excel.BlockchainExcelVO;
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
public interface BlockchainMapper extends BaseMapper<BlockchainEntity> {

    /**
     * 分页查询
     * @param queryDTO
     * @return BlockchainVO
    */
    IPage<BlockchainVO> selectByPage(Page page, @Param("queryDTO") BlockchainQueryDTO queryDTO);

    /**
     * 查询所有导出数据
     * @param queryDTO
     * @return
     */
    List<BlockchainExcelVO> selectAllExportData(@Param("queryDTO")BlockchainQueryDTO queryDTO);

        /**
         * 查询批量导出数据
         * @param idList
         * @return
         */
    List<BlockchainExcelVO> selectBatchExportData(@Param("idList")List<Long> idList);
}
