import cn.hutool.core.io.IoUtil;
import cn.smartrick.metaverse.ShuCangdhApplication;
import cn.smartrick.metaverse.common.domain.ResponseDTO;
import cn.smartrick.metaverse.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//普通单元测试
//@RunWith(JUnit4.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShuCangdhApplication.class)
public class TestJava {
    @Autowired
    private FileService fileService;

    @Test
    public void test1(){
        Matcher matcher1 = Pattern.compile("\\[(\\w*)\\]\\((.*)\\)").matcher("[APP](HTTPS://APP.CHAINMIND.XYZ/)");
        System.out.println(matcher1.matches());
    }

    @Test
    public void testFileUpload() throws IOException {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("测试图片.png","测试图片.png","image/png", IoUtil.toStream(new File("C:\\Users\\28697\\Pictures\\1655025930024-affc27cb-cebc-4af7-9840-eb5d4fdsadasf3e62f.png")));
        MultipartFile[] multipartFiles = new MultipartFile[1];
        multipartFiles[0] = mockMultipartFile;
        ResponseDTO responseDTO = fileService.uploadFiles(multipartFiles);
        System.out.println(responseDTO);
    }

}
