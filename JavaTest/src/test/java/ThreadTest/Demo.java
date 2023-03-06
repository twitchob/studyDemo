package ThreadTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/3/6 10:55
 */
@SpringBootTest(classes = Demo.class)
public class Demo {
    @BeforeAll
    public static void beforeAll() {
        System.out.println("===============Start================");
    }
    @AfterAll
    public static void afterAll() {
        System.out.println("===============End================");
    }


   @Test
    public void test() {
        Thread.State state = Thread.State.NEW;
       System.out.println("state = " + state);
    }
}
