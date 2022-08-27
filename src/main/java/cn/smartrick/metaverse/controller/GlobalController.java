package cn.smartrick.metaverse.controller;

import cn.hutool.core.util.StrUtil;
import cn.smartrick.metaverse.common.constant.CommonConst;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.config.GlobalConfig;
import cn.smartrick.metaverse.exception.BusinessException;
import cn.smartrick.metaverse.service.FileService;
import cn.smartrick.metaverse.utils.QiniuFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局功能接口
 */
@RestController
public class GlobalController {

    @Autowired
    private FileService fileService;
    @Autowired
    private QiniuFileUtil qiniuFileUtil;
    @Autowired
    private GlobalConfig globalConfig;

    /**
     * 上传图片接口
     *
     * @param file
     */
    @PostMapping("/uploadImgs")
    public ResponseDTO uploadImages(MultipartFile[] file) throws IOException {
        if (file != null && file.length > 0) {
            //校验图片格式
            for (MultipartFile img : file) {
                boolean flag = false;
                for (String allowImgExt : CommonConst.allowImgExts) {
                    if (StrUtil.isNotBlank(img.getOriginalFilename()) && img.getOriginalFilename().endsWith(allowImgExt)) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    return ResponseDTO.failMsg("上传失败，包含不支持的图片格式");
                }

                if (img.getSize() > CommonConst.singleImgMaximumSize) {
                    return ResponseDTO.failMsg("上传失败，单张图片大小不超过2MB");
                }
            }
            return fileService.uploadFiles(file);
        }
        return ResponseDTO.failMsg("请至少上传一张图片文件");
    }


    /**
     * 上传图片接口
     *
     * @param file
     */
    @PostMapping("/auImgs")
    public Map<String,String> uploadImageForAvue(MultipartFile file) throws IOException {
        if (file != null) {
            //校验图片格式
            boolean flag = false;
            for (String allowImgExt : CommonConst.allowImgExts) {
                if (StrUtil.isNotBlank(file.getOriginalFilename()) && file.getOriginalFilename().endsWith(allowImgExt)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                throw new BusinessException("上传失败，包含不支持的图片格式");
            }

            if (file.getSize() > CommonConst.singleImgMaximumSize) {
                throw new BusinessException("上传失败，单张图片大小不超过2MB");
            }
            String fileToken = qiniuFileUtil.uploadFile(file);
            HashMap<String, String> resMap = new HashMap<>();
            resMap.put("url", globalConfig.getOssDomain() + "/" +fileToken);
            return resMap;
        }
        throw new BusinessException("请至少上传一张图片文件");
    }
}
