import cn.smartrick.metaverse.ShuCangdhApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Date: 2022/7/27 16:34
 * @Author: SmartRick
 * @Description: TODO
 */
//普通单元测试
@RunWith(JUnit4.class)
//SpringBoo应用测试测试
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShuCangdhApplication.class)
public class DataExtract {

    @Test
    public void dataExtractOne() {
        System.out.println("HEllo");
    }
}
