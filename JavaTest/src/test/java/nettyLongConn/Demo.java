package nettyLongConn;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/5/26 10:18
 */
@SpringBootTest(classes = Demo.class)
public class Demo {

    @Test
    void testLongConn() {
        NettyClient nettyClient = new NettyClient("192.168.1.224", 8080, "abcd1234");
        nettyClient.connect();
        byte[] bytes = new byte[]{28, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 97, 98, 99, 100, 49, 50, 51, 52, 0, 0, 0, 0, 0, 0, 0, 0};
        nettyClient.sendMsg(bytes);

    }

}
