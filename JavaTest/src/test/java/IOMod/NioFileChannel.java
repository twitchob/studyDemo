package IOMod;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/3/8 17:48
 */
@SpringBootTest(classes = NioFileChannel.class)
public class NioFileChannel {


    @Test
    public void test() {
        //创建Intbuffer
        IntBuffer intBuffer = IntBuffer.allocate(5);
        //向buffer中存放数据
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i * 2);
        }
        //从buffer中读取数据
        //将buffer转换，读写切换
        intBuffer.flip();
        intBuffer.position(1);
        intBuffer.limit(3);
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
    /**
     * NIO 写入文件
     *
     * @throws Exception
     */
    @Test
    public void write() throws Exception {
        String str = "hello, world!";

        //创建一个输出流->channel
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");

        //通过fileOutputStream获取对应的FileChannel
        //这个fileChannel真实类型是FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将str放入byteBuffer
        byteBuffer.put(str.getBytes());

        //对byteBuffer进行flip
        byteBuffer.flip();

        fileChannel.write(byteBuffer);
        fileChannel.close();
        fileOutputStream.close();

    }

    /**
     * NIO 读取文件
     *
     * @throws Exception
     */
    @Test
    public void read() throws Exception {
        //创建一个输出流
        File file = new File("d:\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        //通过fileInputStream获取对应的FileChannel
        FileChannel fileChannel = fileInputStream.getChannel();
        //创建一个缓冲区ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //将通道的数据读入到buffer
        fileChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }


    /**
     * NIO 复制文件 即一个buffer完成读写
     * <p>
     * 这样有明显问题,buffer通常很小,需要循环读取
     *
     * @throws Exception
     */
    @Test
    public void copy() throws Exception {
        //读取
        File file = new File("d:\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        channel.read(byteBuffer);


        //写入新文件
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file02.txt");
        channel = fileOutputStream.getChannel();
        byteBuffer.flip();
        channel.write(byteBuffer);
        fileInputStream.close();
        fileOutputStream.close();

    }

    @Test
    public void copy2() throws Exception {

        File file = new File("d:\\file02.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel01 = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file03.txt");
        FileChannel channel02 = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(5);

        //循环读写
        while (true) {
            byteBuffer.clear();
            int read = channel01.read(byteBuffer);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            channel02.write(byteBuffer);
        }

        fileInputStream.close();
        fileOutputStream.close();
    }

    /**
     * transferFrom
     *
     * @throws Exception
     */
    @Test
    public void copy3() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("d:\\a.png");
        FileChannel sourceCh = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("d:\\b.png");
        FileChannel desthCh = fileOutputStream.getChannel();

        //desthCh.transferFrom(sourceCh,0,sourceCh.size());
        sourceCh.transferTo(0, sourceCh.size(), desthCh);

        fileInputStream.close();
        fileOutputStream.close();


    }

    /**
     * 分散和聚集
     */
    @Test
    public void scatterAndGather() throws Exception {
        //serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        //绑定端口到socket,并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接
        SocketChannel SocketChannel = serverSocketChannel.accept();

        //假定从客户端接收8个字节
        int messageLength = 8;
        //循环读取
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long read = SocketChannel.read(byteBuffers);
                byteRead += read;//累计读取的字节数
                System.out.println("byteRead=" + byteRead);
                //使用流打印,看看当前的这个buffer的position和limit
                Arrays.stream(byteBuffers)
                        .map(buffer -> "position=" + buffer.position() + ",limit=" + buffer.limit())
                        .forEach(System.out::println);
            }
            //将所有的buffer进行flip
            Arrays.asList(byteBuffers).forEach(Buffer::flip);
            //将数据读出显示到客户端
            long byteWrite = 0;
            while (byteWrite < messageLength) {
                long write = SocketChannel.write(byteBuffers);
                byteWrite += write;
            }

            //将所有的buffer进行clear
            Arrays.asList(byteBuffers).forEach(Buffer::clear);

            System.out.println("byteRead=" + byteRead + ",byteWrite=" + byteWrite + ",messageLength=" + messageLength);
        }

    }


    /**
     * selector
     */
    @Test
    public void selectorDemo() throws Exception {
        Selector.open();
    }

    /**
     * 服务器端
     */
    @Test
    public void server() throws Exception {
        //创建ssch
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //创建selector
        Selector selector = Selector.open();

        //监听端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //ssch 注册到selector  关心事件是OP_accept
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待连接
        while (true) {
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待1秒,无连接");
                continue;
            }
            //返回>0 获取相关集合   >0 表示获取到关注的事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey key : selectionKeys) {
                //根据key 对应通道的事件做不同处理
                if (key.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //该客户端生成一个socketChannel
                    System.out.println("客户端连接成功,生成一个socketChannel" + socketChannel.hashCode());
                    //设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //注册
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    socketChannel.read(byteBuffer);
                    System.out.println("from 客户端" + new String(byteBuffer.array()));
                }
                //手动从集合中移除当前的selectionKey,防止重复操作
                selectionKeys.remove(key);
            }

        }
    }


    @Test
    public void client() throws Exception {
        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置为非阻塞
        socketChannel.configureBlocking(false);
        //提供服务器端的ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("因为连接需要时间,客户端不会阻塞,可以做其他工作");
            }
        }
        //连接成功,发送数据
        String str = "hello world";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        //发送数据,将buffer数据写入channel
        socketChannel.write(byteBuffer);
        //关闭通道
        System.in.read();

    }

    @Test
    public void client2() throws Exception {
        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置为非阻塞
        socketChannel.configureBlocking(false);
        //提供服务器端的ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("因为连接需要时间,客户端不会阻塞,可以做其他工作");
            }
        }
        //连接成功,发送数据
        String str = "hello world";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        //发送数据,将buffer数据写入channel
        socketChannel.write(byteBuffer);
        //关闭通道
        System.in.read();

    }

}