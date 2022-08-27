package cn.smartrick.metaverse.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.smartrick.metaverse.common.domain.BaseController;
import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.common.domain.ValidateList;
import cn.smartrick.metaverse.domain.dto.query.ApiLogQueryDTO;
import cn.smartrick.metaverse.domain.vo.ApiLogVO;
import cn.smartrick.metaverse.domain.vo.excel.ApiLogExcelVO;
import cn.smartrick.metaverse.service.ApiLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.usermodel.Workbook;
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
 * @date 2022-08-27 16:20:09
 * @since JDK1.8
 */
@RestController
@RequestMapping(value = "/apiLog")
@Api(value = "系统数据监控统计",tags = {"系统数据监控统计"})
public class MonitorController extends BaseController {

    @Autowired
    private ApiLogService apiLogService;

    @ApiOperation(value = "根据Id查询",notes = "@author SmartRick")
    @GetMapping("/{id}")
    public ResponseDTO<ApiLogVO> queryById(@PathVariable @ApiParam(value = "id", name = "id", required = true) Long id) {
        return ResponseDTO.succData(apiLogService.queryById(id));
    }

    @ApiOperation(value = "分页查询",notes = "@author SmartRick")
    @PostMapping("/page")
    public ResponseDTO<PageResultDTO<ApiLogVO>> queryByPage(@RequestBody @ApiParam(value = "分页参数", name = "queryDTO", required = true) ApiLogQueryDTO queryDTO) {
        return apiLogService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "批量导出", notes = "@author SmartRick")
    @PostMapping("/export/batch")
    public void batchExport(@RequestBody @Validated @ApiParam(value = "ids", name = "ids", required = true) ValidateList<Long> idList,
                            HttpServletResponse response) {
        //查询数据
        List<ApiLogExcelVO> apiLogList = apiLogService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ApiLogExcelVO.class, apiLogList);
        downloadExcel("", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SmartRick")
    @PostMapping("/export/all")
    public void exportAll(@RequestBody @Validated @ApiParam(value = "分页参数", name = "queryDTO", required = true) ApiLogQueryDTO queryDTO,
                          HttpServletResponse response) {
        //查询数据
        List<ApiLogExcelVO> apiLogList = apiLogService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ApiLogExcelVO.class, apiLogList);
        downloadExcel("", workbook, response);
    }

    @ApiOperation(value = "今日数据统计",notes = "@author SmartRick")
    @PostMapping("/today")
    public ResponseDTO queryToday(@RequestBody @ApiParam(value = "分页参数", name = "queryDTO", required = true) ApiLogQueryDTO queryDTO) {
        return apiLogService.queryToday();
    }


}
