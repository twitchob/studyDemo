package others;

import com.szaf.api.TestUtils;
import com.szaf.utils.StrUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/4/17 15:39
 */
@SpringBootTest(classes = {TestUseJar.class})
public class TestUseJar {
    @Test
    public void test() {
        String test = TestUtils.getStr("test");
        System.out.println("test = " + test);
        String test1 = StrUtils.getStr("test");
        System.out.println("test1 = " + test1);
    }
}
