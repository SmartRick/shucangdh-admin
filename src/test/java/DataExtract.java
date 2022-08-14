import cn.hutool.core.util.StrUtil;
import cn.smartrick.metaverse.ShuCangdhApplication;
import cn.smartrick.metaverse.common.constant.PlatformEnum;
import cn.smartrick.metaverse.domain.entity.BlockchainEntity;
import cn.smartrick.metaverse.domain.entity.ScBcEntity;
import cn.smartrick.metaverse.domain.entity.ScTagEntity;
import cn.smartrick.metaverse.domain.entity.TagEntity;
import cn.smartrick.metaverse.mapper.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
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
    private BlockchainMapper blockchainMapper;
    @Autowired
    private ShucangPlatformMapper shucangPlatformMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ScBcMapper scBcMapper;
    @Autowired
    private ScTagMapper scTagMapper;


    @Test
    public void dataExtractOne() throws IOException {
        File file = new File("G:\\project\\java\\normal\\NFT\\README2.md");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        Pattern compile = Pattern.compile("\\|(\\d*)\\|(.*)\\|\\[(.*)\\]\\((.*)\\).*");

        while ((line = bufferedReader.readLine()) != null) {
            if (StrUtil.isNotBlank(line)) {
                line = line.trim();
                Matcher matcher = compile.matcher(line);
                if (matcher.matches()) {
                    BlockchainEntity blockchainAddDTO = new BlockchainEntity();
                    blockchainAddDTO.setBlockchain(matcher.group(2));
                    blockchainAddDTO.setLink(matcher.group(3));
                    blockchainMapper.insert(blockchainAddDTO);
                } else {
                    System.out.println(line + "未匹配上");
                }
            }
        }

    }

    @Test
    public void dataExtractOne1() throws IOException {
        File file = new File("G:\\project\\java\\normal\\NFT\\README1.md");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        Pattern compile = Pattern.compile("\\|(\\d*)\\|\\[(.*)\\]\\((.*)\\)\\|(\\w*)\\|(.*)\\|(.*)\\|(.*)\\|(.*)\\|(.*)\\|(.*)");

        while ((line = bufferedReader.readLine()) != null) {
            if (StrUtil.isNotBlank(line)) {
                line = line.trim();
                System.out.println(line);
                Matcher matcher = compile.matcher(line);
                if (matcher.matches()) {
//                    for (int i = 1; i < matcher.groupCount(); i++) {
//                        System.out.println(i + ":" + matcher.group(i));
//                    }
//                    ShucangPlatformEntity shucangPlatformEntity = new ShucangPlatformEntity();
//                    if (StrUtil.isNotBlank(matcher.group(2))) {
//                        shucangPlatformEntity.setName(matcher.group(2));
//                    }
//                    if (StrUtil.isNotBlank(matcher.group(9))) {
//                        MarketModel marketModel = MarketModel.nameOf(matcher.group(9));
//                        if (marketModel != null) shucangPlatformEntity.setMarketModel(marketModel.getCode());
//                    }
//                    shucangPlatformEntity.setName(matcher.group(2));
//                    shucangPlatformEntity.setReviewed(true);
//
//                    if (shucangPlatformMapper.selectCount(new LambdaQueryWrapper<ShucangPlatformEntity>().eq(ShucangPlatformEntity::getName, shucangPlatformEntity.getName())) > 0) {
//                        System.out.println("数藏平台：\"" + shucangPlatformEntity.getName() + "\"已经保存过了");
//                        continue;
//                    }
//
//                    shucangPlatformMapper.insert(shucangPlatformEntity);
//
//                    if (StrUtil.isNotBlank(matcher.group(3))) {
//                        TagEntity tycTag = new TagEntity();
//                        tycTag.setTagType(TagEntity.TAG_TYPE_BACKGROUND);
//                        tycTag.setTagName("天眼查");
//                        tycTag.setLink(matcher.group(3));
//                        tagMapper.insert(tycTag);
//                        scTagMapper.insert(new ScTagEntity(shucangPlatformEntity.getId(), tycTag.getId()));
//                    }
//
//
//                    TagEntity wxgzhTag = new TagEntity();
//                    wxgzhTag.setTagType(TagEntity.TAG_TYPE_SERVER);
//                    wxgzhTag.setTagName(PlatformEnum.valueOf(matcher.group(4).toUpperCase(Locale.ROOT)).getFullName());
//                    tagMapper.insert(wxgzhTag);
//                    scTagMapper.insert(new ScTagEntity(shucangPlatformEntity.getId(), wxgzhTag.getId()));
//
//                    //APP
//                    saveClientTag(shucangPlatformEntity.getId(), matcher.group(6));
//                    //H5
//                    saveClientTag(shucangPlatformEntity.getId(), matcher.group(7));
//
//                    //保存区块链数据
//                    String blockchians = matcher.group(8);
//                    if (blockchians.contains("、")) {
//                        String[] split = blockchians.split("、");
//                        for (String item : split) {
//                            saveScBc(shucangPlatformEntity.getId(), item);
//                        }
//                    } else {
//                        saveScBc(shucangPlatformEntity.getId(), blockchians);
//                    }
                } else {
                    System.out.println(line + "未匹配上");
                }
                System.out.println("-------------------------\n");
            }
        }
    }


    private boolean saveScBc(Integer scId, String bcName) {
        if (StrUtil.isNotBlank(bcName)) {
            List<BlockchainEntity> blockchainEntities = blockchainMapper.selectList(new LambdaQueryWrapper<BlockchainEntity>().eq(BlockchainEntity::getBlockchain, bcName));
            if (blockchainEntities != null && blockchainEntities.size() >= 1) {
                scBcMapper.insert(new ScBcEntity(scId, blockchainEntities.get(0).getId()));
                return true;
            } else {
                System.out.println("未找到名称为：\"" + bcName + "\"的区块链");
                BlockchainEntity blockchainEntity = new BlockchainEntity();
                blockchainEntity.setBlockchain(bcName);
                blockchainMapper.insert(blockchainEntity);
                scBcMapper.insert(new ScBcEntity(scId, blockchainEntity.getId()));
            }
        }
        return false;
    }

    private boolean saveClientTag(Integer scId, String clientText) {
        if (StrUtil.isNotBlank(clientText)) {
            clientText = clientText.trim();
            Matcher matcher1 = Pattern.compile("\\[(\\w*)\\]\\((.*)\\)").matcher(clientText);
            TagEntity clientTag = new TagEntity();
            clientTag.setTagType(TagEntity.TAG_TYPE_CLIENT);

            if (matcher1.matches()) {
                clientTag.setTagName(PlatformEnum.valueOf(matcher1.group(1).toUpperCase(Locale.ROOT)).getFullName());
                clientTag.setLink(matcher1.group(2));
            } else {
                try {
                    PlatformEnum platformEnum = PlatformEnum.valueOf(clientText.toUpperCase(Locale.ROOT));
                    clientTag.setTagName(platformEnum.getFullName());
                } catch (Exception e) {
                    clientTag.setTagName(clientText);
//                    e.printStackTrace();
                }
            }
            tagMapper.insert(clientTag);
            scTagMapper.insert(new ScTagEntity(scId, clientTag.getId()));
        }
        return false;
    }

}
