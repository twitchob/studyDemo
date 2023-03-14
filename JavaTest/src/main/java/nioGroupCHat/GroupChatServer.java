package nioGroupCHat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Base64;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/3/9 18:03
 */
public class GroupChatServer {
    private final int PORT = 6667;
    private ServerSocketChannel listenChannel;
    private Selector selector;

    //构造器
    public GroupChatServer() {
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.bind(new InetSocketAddress("localhsot", 6666));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //监听
    public void listen() {
        try {
            while (true) {
                int count = selector.select(2000);
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = listenChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_ACCEPT);

                        }
                        if (key.isReadable()) {

                            //todo 读取方法
                        }
                        iterator.remove();

                    }
                } else {
                    System.out.println("等待____");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void read(SelectionKey key) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(byteBuffer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
