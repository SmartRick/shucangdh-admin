package cn.smartrick.metaverse.controller;

import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.BaseController;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.common.domain.ValidateList;
import cn.smartrick.metaverse.domain.dto.add.ShucangPlatformAddDTO;
import cn.smartrick.metaverse.domain.dto.update.ShucangPlatformUpdateDTO;
import cn.smartrick.metaverse.domain.dto.query.ShucangPlatformQueryDTO;
import cn.smartrick.metaverse.domain.vo.ShucangPlatformVO;
import cn.smartrick.metaverse.domain.vo.excel.ShucangPlatformExcelVO;
import cn.smartrick.metaverse.service.ShucangPlatformService;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @date 2022-07-27 10:26:37
 * @since JDK1.8
 */
@RestController
@RequestMapping(value = "/shucangPlatform")
@Api(value = "数藏平台", tags = {"数藏平台"})
public class ShucangPlatformController extends BaseController {

    @Autowired
    private ShucangPlatformService shucangPlatformService;

    @ApiOperation(value = "根据Id查询", notes = "@author SmartRick")
    @GetMapping("/{id}")
    public ResponseDTO<ShucangPlatformVO> queryById(@PathVariable @ApiParam(value = "id", name = "id", required = true) Long id) {
        return ResponseDTO.succData(shucangPlatformService.queryById(id));
    }

    @ApiOperation(value = "分页查询", notes = "@author SmartRick")
    @PostMapping("/page")
    public ResponseDTO<PageResultDTO<ShucangPlatformVO>> queryByPage(@RequestBody @ApiParam(value = "分页参数", name = "queryDTO", required = true) ShucangPlatformQueryDTO queryDTO) {
        return shucangPlatformService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加", notes = "@author SmartRick")
    @PostMapping("/")
    public ResponseDTO<String> add(@RequestBody @Validated @ApiParam(value = "", name = "shucangPlatform", required = true) ShucangPlatformAddDTO addDTO) {
        return shucangPlatformService.add(addDTO);
    }

    @ApiOperation(value = "修改", notes = "@author SmartRick")
    @PutMapping("/")
    public ResponseDTO<String> modify(@RequestBody @Validated @ApiParam(value = "", name = "shucangPlatform", required = true) ShucangPlatformUpdateDTO updateDTO) {
        return shucangPlatformService.modify(updateDTO);
    }

    @ApiOperation(value = "删除", notes = "@author SmartRick")
    @DeleteMapping("/{id}")
    public ResponseDTO<String> remove(@PathVariable @Validated @ApiParam(value = "id", name = "id", required = true) Long id) {
        return shucangPlatformService.remove(id);
    }

    @ApiOperation(value = "批量删除", notes = "@author SmartRick")
    @DeleteMapping("/removes")
    public ResponseDTO<String> removeByIds(@RequestBody @Validated @ApiParam(value = "ids", name = "ids", required = true) ValidateList<Long> ids) {
        return shucangPlatformService.removeByIds(ids);
    }

    @ApiOperation(value = "批量导出", notes = "@author SmartRick")
    @PostMapping("/export/batch")
    public void batchExport(@RequestBody @Validated @ApiParam(value = "ids", name = "ids", required = true) ValidateList<Long> idList,
                            HttpServletResponse response) {
        //查询数据
        List<ShucangPlatformExcelVO> shucangPlatformList = shucangPlatformService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ShucangPlatformExcelVO.class, shucangPlatformList);
        downloadExcel("", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SmartRick")
    @PostMapping("/export/all")
    public void exportAll(@RequestBody @Validated @ApiParam(value = "分页参数", name = "queryDTO", required = true) ShucangPlatformQueryDTO queryDTO,
                          HttpServletResponse response) {
        //查询数据
        List<ShucangPlatformExcelVO> shucangPlatformList = shucangPlatformService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ShucangPlatformExcelVO.class, shucangPlatformList);
        downloadExcel("", workbook, response);
    }


    @ApiOperation(value = "点赞数藏平台", notes = "@author SmartRick")
    @GetMapping("/like")
    public ResponseDTO like(@RequestParam("scId") @ApiParam(value = "数藏平台Id", name = "scId", required = true) Integer scId) {
        return shucangPlatformService.like(scId);
    }

    @ApiOperation(value = "浏览数藏平台", notes = "@author SmartRick")
    @GetMapping("/browse")
    public ResponseDTO browse(@RequestParam("scId") @ApiParam(value = "数藏平台Id", name = "scId", required = true) Integer scId) {
        return shucangPlatformService.browse(scId);
    }
}
