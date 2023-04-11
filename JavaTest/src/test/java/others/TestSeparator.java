package others;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/4/10 17:36
 */
@SpringBootTest(classes = TestSeparator.class)
public class TestSeparator {
    @Test
    public void test() {
        //q: java  System.lineSeparator()是什么
        //a: 用于分隔行的字符串。在 UNIX 系统上是“/n”，在 Microsoft Windows 系统上是“/r/n”。
        //q: 怎么把它写在文件中
        //a: 用FileWriter
        //q:  我想写一个字符串 "123/n456/n789" 到文件中
        //a: 用FileWriter
        String s = System.lineSeparator();
        System.out.println("123"+s+"456"+s+"789");

    }
}
