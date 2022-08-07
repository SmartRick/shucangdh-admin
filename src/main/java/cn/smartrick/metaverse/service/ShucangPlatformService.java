package cn.smartrick.metaverse.service;

import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.domain.dto.add.ShucangPlatformAddDTO;
import cn.smartrick.metaverse.domain.dto.query.ShucangPlatformQueryDTO;
import cn.smartrick.metaverse.domain.dto.update.ShucangPlatformUpdateDTO;
import cn.smartrick.metaverse.domain.vo.ShucangPlatformVO;
import cn.smartrick.metaverse.domain.vo.excel.ShucangPlatformExcelVO;

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
public interface ShucangPlatformService {

    /**
     * 根据id查询
     * @author SmartRick
     */
    public ShucangPlatformVO queryById(Long id);

    /**
     * 分页查询
     * @author SmartRick
     */
    public ResponseDTO<PageResultDTO<ShucangPlatformVO>> queryByPage(ShucangPlatformQueryDTO queryDTO);

    /**
     * 添加
     * @author SmartRick
     */
    public ResponseDTO<String> add(ShucangPlatformAddDTO addDTO);

    /**
     * 编辑
     * @author SmartRick
     */
    public ResponseDTO<String> modify(ShucangPlatformUpdateDTO updateDTO);

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
    public List<ShucangPlatformExcelVO> queryAllExportData(ShucangPlatformQueryDTO queryDTO);

    /**
     * 批量查询导出对象
     * @author SmartRick
     */
    public List<ShucangPlatformExcelVO> queryBatchExportData(List<Long> idList);



    /**
     * 点赞数藏平台
     * @author SmartRick
     */
    public ResponseDTO like(Integer scId);


    /**
     * 浏览数藏平台
     * @author SmartRick
     */
    public ResponseDTO browse(Integer scId);


    /**
     * 数字藏品平台投稿
     * @author SmartRick
     */
    public ResponseDTO<String> contribute(ShucangPlatformAddDTO addDTO);

    /**
     * 数字藏品平台投稿
     * @author SmartRick
     */
    public ResponseDTO<String> reviewPlatform(Integer scId,boolean isPass);
}
