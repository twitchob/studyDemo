package others;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/4/10 16:43
 */
@SpringBootTest(classes = JarTest.class)
public class JarTest {
    @Test
    public void test() {
        System.out.println(com.szaf.TestUtils.getStr("123"));
    }
}
