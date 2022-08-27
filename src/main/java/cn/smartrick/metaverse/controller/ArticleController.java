package cn.smartrick.metaverse.controller;

import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.BaseController;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.common.domain.ValidateList;
import cn.smartrick.metaverse.domain.dto.add.ArticleAddDTO;
import cn.smartrick.metaverse.domain.dto.update.ArticleUpdateDTO;
import cn.smartrick.metaverse.domain.dto.query.ArticleQueryDTO;
import cn.smartrick.metaverse.domain.vo.ArticleVO;
import cn.smartrick.metaverse.domain.vo.excel.ArticleExcelVO;
import cn.smartrick.metaverse.service.ArticleService;
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
 * @date 2022-08-27 16:20:10
 * @since JDK1.8
 */
@RestController
@RequestMapping(value = "/article")
@Api(value = "数藏资讯文章",tags = {"数藏资讯文章"})
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "根据Id查询",notes = "@author SmartRick")
    @GetMapping("/{id}")
    public ResponseDTO<ArticleVO> queryById(@PathVariable @ApiParam(value = "id", name = "id", required = true) Long id) {
        return ResponseDTO.succData(articleService.queryById(id));
    }

    @ApiOperation(value = "分页查询",notes = "@author SmartRick")
    @PostMapping("/page")
    public ResponseDTO<PageResultDTO<ArticleVO>> queryByPage(@RequestBody @ApiParam(value = "分页参数", name = "queryDTO", required = true) ArticleQueryDTO queryDTO) {
        return articleService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加",notes = "@author SmartRick")
    @PostMapping("/")
    public ResponseDTO<String> add(@RequestBody @Validated @ApiParam(value = "", name = "article", required = true) ArticleAddDTO addDTO){
        return articleService.add(addDTO);
    }

    @ApiOperation(value="修改",notes = "@author SmartRick")
    @PutMapping("/")
    public ResponseDTO<String> modify(@RequestBody @Validated @ApiParam(value = "", name = "article", required = true) ArticleUpdateDTO updateDTO){
        return articleService.modify(updateDTO);
    }

    @ApiOperation(value="删除",notes = "@author SmartRick")
    @DeleteMapping("/{id}")
    public ResponseDTO<String> remove(@PathVariable @Validated @ApiParam(value = "id", name = "id", required = true) Long id) {
        return articleService.remove(id);
    }

    @ApiOperation(value="批量删除",notes = "@author SmartRick")
    @DeleteMapping("/removes")
    public ResponseDTO<String> removeByIds(@RequestBody @Validated @ApiParam(value = "ids", name = "ids", required = true) ValidateList<Long> ids) {
        return articleService.removeByIds(ids);
    }

    @ApiOperation(value = "批量导出", notes = "@author SmartRick")
    @PostMapping("/export/batch")
    public void batchExport(@RequestBody @Validated @ApiParam(value = "ids", name = "ids", required = true) ValidateList<Long> idList,
                            HttpServletResponse response) {
        //查询数据
        List<ArticleExcelVO> articleList = articleService.queryBatchExportData(idList);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ArticleExcelVO.class, articleList);
        downloadExcel("", workbook, response);
    }

    @ApiOperation(value = "导出全部", notes = "@author SmartRick")
    @PostMapping("/export/all")
    public void exportAll(@RequestBody @Validated @ApiParam(value = "分页参数", name = "queryDTO", required = true) ArticleQueryDTO queryDTO,
                          HttpServletResponse response) {
        //查询数据
        List<ArticleExcelVO> articleList = articleService.queryAllExportData(queryDTO);
        //导出操作
        ExportParams ex = new ExportParams("", "Sheet1");
        Workbook workbook = ExcelExportUtil.exportExcel(ex, ArticleExcelVO.class, articleList);
        downloadExcel("", workbook, response);
    }

}
