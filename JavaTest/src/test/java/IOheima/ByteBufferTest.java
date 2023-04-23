package IOheima;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/4/6 21:43
 */
@SpringBootTest(classes = ByteBufferTest.class)
public class ByteBufferTest {

    /**
     * buffer 和 channel 的交互 简单demo
     */
    @Test
    public void bufferTest1() {

        try (FileChannel channel = new FileInputStream("src\\test\\java\\IOheima\\data.txt").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(10);

            while (true) {
                int read = channel.read(buffer);
                if (read == -1) {
                    break;
                }
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.println((char) buffer.get());
                }
                buffer.clear();
            }
        } catch (IOException e) {
        }
    }
}
