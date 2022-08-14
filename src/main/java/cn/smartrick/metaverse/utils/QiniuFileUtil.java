package cn.smartrick.metaverse.utils;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.smartrick.metaverse.config.GlobalConfig;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * @author SmartRick
 * @date 2022年1月3日18:54:34
 * 七牛云OSS SDK封装，上传文件工具类
 */
@Component
public class QiniuFileUtil {

    @Resource
    private GlobalConfig globalConfig;

    //构造一个带指定Region对象的配置类
    private Configuration cfg = new Configuration(Region.region2());

    private UploadManager uploadManager = new UploadManager(cfg);

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        String uploadToken = getAuth().uploadToken(globalConfig.getOssBucket());
        String originalFilename = file.getOriginalFilename();
        if (StrUtil.isEmpty(originalFilename)) return null;
        // 文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileKey = UUID.randomUUID() + suffix;
        Response response = uploadManager.put(file.getInputStream(), fileKey, uploadToken,
                getPutPolicy(), null);
        DefaultPutRet putRet = JsonUtil.toBean(response.bodyString(), DefaultPutRet.class);

//        System.out.println(putRet.key);
//        System.out.println(putRet.hash);
        return fileKey;
    }

    public String getDownloadPrivatePath(String fileName) {
        try {
            return getPrivateFile(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 定义七牛云上传的相关策略
     */
    public StringMap getPutPolicy() {
        StringMap stringMap = new StringMap();
        stringMap.put("returnBody",
                "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
        return stringMap;
    }

    /**
     * 删除七牛云上的相关文件
     *
     * @param key
     * @return
     * @throws QiniuException
     */
    public String delete(String key) throws QiniuException {
        BucketManager bucketManager = new BucketManager(getAuth(), cfg);
        Response response = bucketManager.delete(globalConfig.getOssBucket(), key);
        int retry = 0;
        //判断是否需要 重试 删除 且重试次数为3
        while (response.needRetry() && retry++ < 3) {
            System.out.println("正在尝试删除");
            response = bucketManager.delete(globalConfig.getOssBucket(), key);
        }
        return response.statusCode == 200 ? "删除成功!" : "删除失败!";
    }

    /**
     * 获取私有空间文件
     *
     * @param fileKey 要下载的文件名
     * @return
     */
    public String getPrivateFile(String fileKey) throws Exception {
        String encodedFileName = URLEncoder.encode(fileKey, "utf-8").replace("+", "%20");
        String publicUrl = String.format("%s/%s", globalConfig.getOssDomain(), encodedFileName);
//        //1小时，可以自定义链接过期时间
//        long expireInSeconds = 3600;
        return  getAuth().privateDownloadUrl(publicUrl);
    }


    /**
     * 通过发送http get 请求获取文件资源
     *
     * @return
     */
    public byte[] loadFile(String fileKey) {
        String downloadUrl = getDownloadPrivatePath(fileKey);
        System.out.println(downloadUrl);
        HttpRequest get = HttpUtil.createGet(downloadUrl);
        HttpResponse response = get.execute();
        return response.bodyBytes();
    }

//
//    public byte[] inputString2ByteArray(InputStream inputStream) {
//        ByteArrayOutputStream byteArrayOutputStream = null;
//        byte[] buffer = new byte[1024];
//        int len = -1;
//        try {
//            byteArrayOutputStream = new ByteArrayOutputStream();
//            while ((len = inputStream.read(buffer)) != -1) {
//                byteArrayOutputStream.write(buffer, 0, len);
//            }
//            byteArrayOutputStream.flush();
//            return byteArrayOutputStream.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (byteArrayOutputStream != null) {
//                try {
//                    byteArrayOutputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;
//
//    }


    private Auth getAuth() {
        return Auth.create(globalConfig.getOssAccessKey(), globalConfig.getOssSecretKey());
    }

}
