package cn.smartrick.metaverse.service;

import cn.smartrick.metaverse.common.domain.ResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件操作接口
 */
public interface FileService {
    ResponseDTO uploadFiles(MultipartFile[] files) throws IOException;
}
