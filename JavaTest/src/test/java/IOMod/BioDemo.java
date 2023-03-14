package IOMod;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/3/6 18:01
 */
@SpringBootTest(classes = BioDemo.class)
public class BioDemo {
    @Test
    public void test() throws Exception {

        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(7777);
        System.out.println("服务器启动了");

        while (true) {
            //监听，等待客户端连接
            System.out.println("等待连接...");
            final java.net.Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            //创建一个线程，与客户端通讯（单独写一个方法）
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //可以和客户端通讯
                    handler(socket);
                }
            });
        }

    }

    //编写一个handler方法，和客户端通讯
    public static void handler(java.net.Socket socket) {
        try {
            System.out.println("线程信息 id = " + Thread.currentThread().getId() + " 名字 = " + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            //通过socket获取输入流
            java.io.InputStream inputStream = socket.getInputStream();

            //循环的读取客户端发送的数据
            while (true) {
                System.out.println("线程信息 id = " + Thread.currentThread().getId() + " 名字 = " + Thread.currentThread().getName());
                System.out.println("read...");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    //输出客户端发送的数据
                    System.out.println(new String(bytes, 0, read)); //输出客户端发送的数据
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
