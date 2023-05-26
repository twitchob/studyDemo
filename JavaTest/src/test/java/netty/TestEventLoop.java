package netty;

import io.netty.channel.nio.NioEventLoopGroup;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/4/23 16:01
 */
@SpringBootTest(classes = TestEventLoop.class)
public class TestEventLoop {
    @Test
    public void test() {
        NioEventLoopGroup group = new NioEventLoopGroup(2);
        System.out.println(group.next());
        System.out.println(group.next());
        System.out.println(group.next());


        //执行普通任务
        group.next().submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("执行普通任务");
        });


        //执行定时任务
        group.next().schedule(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("执行定时任务");
        }, 1, TimeUnit.SECONDS);
    }
}
