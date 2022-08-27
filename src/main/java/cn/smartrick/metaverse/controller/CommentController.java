package cn.smartrick.metaverse.controller;

import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.BaseController;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.common.domain.ValidateList;
import cn.smartrick.metaverse.domain.dto.add.CommentAddDTO;
import cn.smartrick.metaverse.domain.dto.query.CommentQueryDTO;
import cn.smartrick.metaverse.domain.vo.CommentVO;
import cn.smartrick.metaverse.domain.vo.excel.CommentExcelVO;
import cn.smartrick.metaverse.service.CommentService;
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
 * [ 该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户 ]
 *
 * @author SmartRick
 * @version 1.0
 * @date 2022-08-27 16:20:10
 * @since JDK1.8
 */
@RestController
@RequestMapping(value = "/comment")
@Api(value = "评论模块",tags = {"该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户"})
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "根据Id查询该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户",notes = "@author SmartRick")
    @GetMapping("/{id}")
    public ResponseDTO<CommentVO> queryById(@PathVariable @ApiParam(value = "该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户id", name = "id", required = true) Long id) {
        return ResponseDTO.succData(commentService.queryById(id));
    }

    @ApiOperation(value = "分页查询该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户",notes = "@author SmartRick")
    @PostMapping("/page")
    public ResponseDTO<PageResultDTO<CommentVO>> queryByPage(@RequestBody @ApiParam(value = "分页参数", name = "queryDTO", required = true) CommentQueryDTO queryDTO) {
        return commentService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户",notes = "@author SmartRick")
    @PostMapping("/")
    public ResponseDTO<String> add(@RequestBody @Validated @ApiParam(value = "该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户", name = "comment", required = true) CommentAddDTO addDTO){
        return commentService.add(addDTO);
    }

    @ApiOperation(value="删除该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户",notes = "@author SmartRick")
    @DeleteMapping("/{id}")
    public ResponseDTO<String> remove(@PathVariable @Validated @ApiParam(value = "该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户id", name = "id", required = true) Long id) {
        return commentService.remove(id);
    }

    @ApiOperation(value="批量删除该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户",notes = "@author SmartRick")
    @DeleteMapping("/removes")
    public ResponseDTO<String> removeByIds(@RequestBody @Validated @ApiParam(value = "该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户ids", name = "ids", required = true) ValidateList<Long> ids) {
        return commentService.removeByIds(ids);
    }

    @ApiOperation(value = "批量导出", notes = "@author SmartRick")
    @PostMapping("/export/batch")
    public void batchExport(@RequestBody @Validated @ApiParam(value = "该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户ids", name = "ids", required = true) ValidateList<Long> idList,
                            HttpServletResponse response) {
        //查询数据
        List<CommentExcelVO> commentList = commentService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, CommentExcelVO.class, commentList);
        downloadExcel("该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SmartRick")
    @PostMapping("/export/all")
    public void exportAll(@RequestBody @Validated @ApiParam(value = "分页参数", name = "queryDTO", required = true) CommentQueryDTO queryDTO,
                          HttpServletResponse response) {
        //查询数据
        List<CommentExcelVO> commentList = commentService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, CommentExcelVO.class, commentList);
        downloadExcel("该评论功能为阉割版，仅保留最基础评论内容功能，不支持回复，不绑定用户", workbook, response);
    }

}
