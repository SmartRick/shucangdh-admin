package cn.smartrick.metaverse.controller;

import cn.smartrick.metaverse.common.domain.BaseController;
import cn.smartrick.metaverse.common.domain.PageResultDTO;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.common.domain.ValidateList;
import cn.smartrick.metaverse.domain.dto.add.NoticeAddDTO;
import cn.smartrick.metaverse.domain.dto.query.NoticeQueryDTO;
import cn.smartrick.metaverse.domain.dto.update.NoticeUpdateDTO;
import cn.smartrick.metaverse.domain.vo.NoticeVO;
import cn.smartrick.metaverse.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * [  ]
 *
 * @author SmartRick
 * @version 1.0
 * @date 2022-08-27 16:20:10
 * @since JDK1.8
 */
@RestController
@RequestMapping(value = "/notice")
@Api(value = "系统公告",tags = {"系统公告"})
public class NoticeController extends BaseController {

    @Autowired
    private NoticeService noticeService;

    @ApiOperation(value = "根据Id查询",notes = "@author SmartRick")
    @GetMapping("/{id}")
    public ResponseDTO<NoticeVO> queryById(@PathVariable @ApiParam(value = "id", name = "id", required = true) Long id) {
        return ResponseDTO.succData(noticeService.queryById(id));
    }

    @ApiOperation(value = "分页查询",notes = "@author SmartRick")
    @PostMapping("/page")
    public ResponseDTO<PageResultDTO<NoticeVO>> queryByPage(@RequestBody @ApiParam(value = "分页参数", name = "queryDTO", required = true) NoticeQueryDTO queryDTO) {
        return noticeService.queryByPage(queryDTO);
    }

    @ApiOperation(value = "添加",notes = "@author SmartRick")
    @PostMapping("/")
    public ResponseDTO<String> add(@RequestBody @Validated @ApiParam(value = "", name = "notice", required = true) NoticeAddDTO addDTO){
        return noticeService.add(addDTO);
    }

    @ApiOperation(value="修改",notes = "@author SmartRick")
    @PutMapping("/")
    public ResponseDTO<String> modify(@RequestBody @Validated @ApiParam(value = "", name = "notice", required = true) NoticeUpdateDTO updateDTO){
        return noticeService.modify(updateDTO);
    }

    @ApiOperation(value="删除",notes = "@author SmartRick")
    @DeleteMapping("/{id}")
    public ResponseDTO<String> remove(@PathVariable @Validated @ApiParam(value = "id", name = "id", required = true) Long id) {
        return noticeService.remove(id);
    }

    @ApiOperation(value="批量删除",notes = "@author SmartRick")
    @DeleteMapping("/removes")
    public ResponseDTO<String> removeByIds(@RequestBody @Validated @ApiParam(value = "ids", name = "ids", required = true) ValidateList<Long> ids) {
        return noticeService.removeByIds(ids);
    }

}
