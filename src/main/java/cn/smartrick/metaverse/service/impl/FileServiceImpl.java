package cn.smartrick.metaverse.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.config.GlobalConfig;
import cn.smartrick.metaverse.exception.BusinessException;
import cn.smartrick.metaverse.service.FileService;
import cn.smartrick.metaverse.utils.QiniuFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private QiniuFileUtil qiniuFileUtil;
    @Autowired
    private GlobalConfig globalConfig;

    @Override
    public ResponseDTO uploadFiles(MultipartFile[] files) throws IOException {
        List<String> fileKeyList = new ArrayList<String>();
        for (MultipartFile file : files) {
            String fileKey = qiniuFileUtil.uploadFile(file);
            if (StrUtil.isBlank(fileKey)) {
                throw new BusinessException("上传文件失败：" + file.getOriginalFilename());
            }
            fileKeyList.add(globalConfig.getOssDomain()+"/"+fileKey);
        }
        return ResponseDTO.succData(fileKeyList);
    }

}
