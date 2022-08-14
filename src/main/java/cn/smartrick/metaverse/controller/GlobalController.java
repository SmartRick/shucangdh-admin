package cn.smartrick.metaverse.controller;

import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

/**
 * 全局功能接口
 */
@RestController
public class GlobalController {

    @Autowired
    private FileService fileService;


    /**
     * 上传图片接口
     *
     * @param file
     */
    @PostMapping("/uploadImgs")
    public ResponseDTO uploadImages(MultipartFile[] file) throws IOException {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("url","http://rgl6i3hwh.hn-bkt.clouddn.com");
        objectObjectHashMap.put("name","http://rgl6i3hwh.hn-bkt.clouddn.com/105cd3f3-4c07-4f9c-80df-c812b7291f5d.png");
        return ResponseDTO.succData(objectObjectHashMap);
//        if (file != null && file.length > 0) {
//            //校验图片格式
//            for (MultipartFile img : file) {
//                boolean flag = false;
//                for (String allowImgExt : CommonConst.allowImgExts) {
//                    if (StrUtil.isNotBlank(img.getOriginalFilename()) && img.getOriginalFilename().endsWith(allowImgExt)) {
//                        flag = true;
//                        break;
//                    }
//                }
//                if (!flag) {
//                    return ResponseDTO.failMsg("上传失败，包含不支持的图片格式");
//                }
//
//                if (img.getSize() > CommonConst.singleImgMaximumSize) {
//                    return ResponseDTO.failMsg("上传失败，单张图片大小不超过2MB");
//                }
//            }
//            return fileService.uploadFiles(file);
//        }
//        return ResponseDTO.failMsg("请至少上传一张图片文件");
    }
}
