package IOMod;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/3/7 10:13
 */
@SpringBootTest(classes = NioDemo.class)

public class NioDemo {

    @Test
    public void server() throws Exception {
        //创建ServerSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //bind
        serverSocketChannel.socket().bind(new InetSocketAddress("localhost", 5555));
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);

        //创建Selector
        Selector selector = Selector.open();
        //将serverSocketChannel注册到selector上,并且selector对客户端accept连接操作感兴趣
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {

            if (selector.select(3000) == 0) {
                System.out.println("客户端等待了3秒,没有连接");
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey key : selectionKeys) {
                if (key.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    socketChannel.read(byteBuffer);
                    System.out.println("地址:"+socketChannel.getLocalAddress()+"说:"+new String(byteBuffer.array()));

                }
                selectionKeys.remove(key);
            }

        }

    }

    @Test
    public void client1() throws Exception {
        //从键盘输入字符
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 5555));
        while (!socketChannel.finishConnect()) {
            System.out.println("连接中");
        }
        String str = "hello";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        socketChannel.write(byteBuffer);
        System.in.read();

    }



}
