import cn.hutool.core.util.StrUtil;
import cn.smartrick.metaverse.ShuCangdhApplication;
import cn.smartrick.metaverse.domain.dto.add.BlockchainAddDTO;
import cn.smartrick.metaverse.service.BlockchainService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Date: 2022/7/27 16:34
 * @Author: SmartRick
 * @Description: TODO
 */
//普通单元测试
//@RunWith(JUnit4.class)
//SpringBoo应用测试测试
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShuCangdhApplication.class)
public class DataExtract {

    @Autowired
    private BlockchainService blockchainService;


    @Test
    public void dataExtractOne() throws IOException {
        File file = new File("D:\\workdir\\java_work\\NFT\\README.md");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        Pattern compile = Pattern.compile("\\|(\\d)\\|(.*)\\|\\[(.*)\\]\\((.*)\\).*");

        while ((line = bufferedReader.readLine()) != null) {
            if (StrUtil.isNotBlank(line)) {
                line = line.trim();
                Matcher matcher = compile.matcher(line);
                if (matcher.matches()) {
                    BlockchainAddDTO blockchainAddDTO = new BlockchainAddDTO();
                    blockchainAddDTO.setBlockchain(matcher.group(2));
                    blockchainAddDTO.setLink(matcher.group(3));
                    blockchainService.add(blockchainAddDTO);
                }else{
                    System.out.println(line+"未匹配上");
                }
            }
        }

    }

    @Test
    public void dataExtractOne() throws IOException {
        File file = new File("D:\\workdir\\java_work\\NFT\\README.md");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        Pattern compile = Pattern.compile("\\|(\\d)\\|(.*)\\|\\[(.*)\\]\\((.*)\\).*");

        while ((line = bufferedReader.readLine()) != null) {
            if (StrUtil.isNotBlank(line)) {
                line = line.trim();
                Matcher matcher = compile.matcher(line);
                if (matcher.matches()) {
                    BlockchainAddDTO blockchainAddDTO = new BlockchainAddDTO();
                    blockchainAddDTO.setBlockchain(matcher.group(2));
                    blockchainAddDTO.setLink(matcher.group(3));
                    blockchainService.add(blockchainAddDTO);
                }else{
                    System.out.println(line+"未匹配上");
                }
            }
        }

    }
}
