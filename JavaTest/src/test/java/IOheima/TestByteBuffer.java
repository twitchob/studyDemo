package IOheima;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/8/3 9:38
 */
public class TestByteBuffer {


    /**
     * buffer 简介
     */
    @Test
    void testByteBuffer() {
        try (FileChannel channel = new FileInputStream("D:\\learnPlace\\TestToolForZZY\\JavaTest\\src\\test\\java\\IOheima\\data.txt").getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                int read = channel.read(buffer);
                if (read == -1) {
                    break;
                }
                buffer.flip();   //切换到读模式
                while (buffer.hasRemaining()) {
                    System.out.println((char) buffer.get());
                }
                buffer.clear(); //切换到写模式
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 分配空间
     */
    @Test
    void testAllocate() {
        /**
         * allocate 分配空间 不可以动态扩容 NEtty中做了增强可以动态扩容
         * allocate heap 分配堆内存 受JVM控制  class java.nio.HeapByteBuffer  效率低 GC
         * allocateDirect 直接分配内存 不受JVM控制  class java.nio.DirectByteBuffer  效率高(少一次拷贝) 不GC
         */
        System.out.println(ByteBuffer.allocate(10).getClass());
        System.out.println(ByteBuffer.allocateDirect(10).getClass());


    }

    /**
     * 写入数据  put
     */
    @Test
    void testPut() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
    }

    /**
     * 读取数据
     */
    @Test
    void testGet() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        buffer.flip();
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
    }

    /**
     * rewind
     */
    @Test
    void testRewind() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        buffer.flip();
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        buffer.rewind();
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
    }

    /**
     * mark reset
     */
    @Test
    void testMarkReset() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        buffer.put((byte) 4);
        buffer.flip();
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        buffer.mark();
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        buffer.reset();
        System.out.println(buffer.get());
        System.out.println(buffer.get());
    }

    /**
     * get
     */
    @Test
    void testGet2() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        buffer.put((byte) 4);
        buffer.flip();

        System.out.println(buffer.get(3));
    }


    /**
     * 字符串转换为ByteBuffer  getBytes()
     */
    @Test
    void testStringToByteBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("hello".getBytes());
        buffer.flip();
        //读出来
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        System.out.println(new String(bytes));
    }

    /**
     * 字符串转换为ByteBuffer2  StandardCharsets.encode()
     * 自动切换到读模式
     */
    @Test
    void testStringToByteBuffer2() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        ByteBuffer hello = StandardCharsets.UTF_8.encode("hello");
        //读出来
        byte[] bytes = new byte[hello.limit()];
        hello.get(bytes);
        System.out.println(new String(bytes));
    }

    /**
     * 字符串转换为ByteBuffer3 warp
     * 自动切换到读模式
     */
    @Test
    void testStringToByteBuffer3() {
        ByteBuffer wrap = ByteBuffer.wrap("hello".getBytes());
        //读出来
        byte[] bytes = new byte[wrap.limit()];
        wrap.get(bytes);
        System.out.println(new String(bytes));
    }


    /**
     * 分散读
     */
    @Test
    void testScatterRead() {
        try (FileChannel channel = new FileInputStream("D:\\learnPlace\\TestToolForZZY\\JavaTest\\src\\test\\java\\IOheima\\data.txt").getChannel()) {
            ByteBuffer buffer1 = ByteBuffer.allocate(3);
            ByteBuffer buffer2 = ByteBuffer.allocate(3);
            ByteBuffer buffer3 = ByteBuffer.allocate(5);
            ByteBuffer[] buffers = {buffer1, buffer2, buffer3};


            channel.read(buffers);
            for (ByteBuffer buffer : buffers) {
                buffer.flip();
                byte[] bytes = new byte[buffer.limit()];
                buffer.get(bytes);
                System.out.println(new String(bytes));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 聚集写
     */
    @Test
    void testGatherWrite() {
        ByteBuffer hello = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer world = StandardCharsets.UTF_8.encode("world");
        ByteBuffer java = StandardCharsets.UTF_8.encode("java");
        ByteBuffer[] buffers = {hello, world, java};
        try (FileChannel channel = new RandomAccessFile("D:\\learnPlace\\TestToolForZZY\\JavaTest\\src\\test\\java\\IOheima\\data2.txt", "rw").getChannel()) {
            channel.write(buffers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 黏包 半包
     */
    @Test
    void testPackage() {
        ByteBuffer source = ByteBuffer.allocate(32);
        source.putInt("Hello,world\nI'm zzy\nHo".getBytes().length);
        split(source);
        source.put("w are you?\n".getBytes());
        split(source);
    }
    private void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                int length = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j < length; j++) {
                    target.put(source.get());
                }
                target.flip();
                System.out.println(StandardCharsets.UTF_8.decode(target));
            }
        }
        source.compact();
    }
}