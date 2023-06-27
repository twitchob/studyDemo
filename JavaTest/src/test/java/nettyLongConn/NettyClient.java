package nettyLongConn;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/5/26 9:48
 */
public class NettyClient implements Runnable {


    private final String host;
    private final int port;
    private final String password;


    private EventLoopGroup group;
    private Channel channel;
    private ChannelFuture future;

    private int MAX_RETRY = 3; // 最大重试次数
    private int RETRY_DELAY = 5000; // 重试间隔时间（毫秒）
    private int retryCount = 0; // 当前重试次\

    @Setter
    private byte[] res;


    @Setter
    private NettyClientHandler nettyClientHandler;

    public NettyClient(String host, int port, String password) {
        this.host = host;
        this.port = port;
        this.password = password;
        connect();
    }

    public void connect() {
        if (group != null) {
            group.shutdownGracefully();
        }
        group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(io.netty.channel.socket.nio.NioSocketChannel.class)
                    .handler(new NettyClientInitializer(NettyClient.this));
            //连接服务器
            future = bootstrap.connect(host, port);

            //断线重连
            future.addListener((ChannelFutureListener) channelFuture -> {
                if (channelFuture.isSuccess()) {
                    channel = channelFuture.channel();
                } else {
                    channelFuture.channel().eventLoop().schedule(() -> {
                        System.out.println("开始重连");
                        connect();
                        //重连次数
                        retryCount++;
                        if (retryCount >= MAX_RETRY) {
                            System.out.println("重连次数超过最大重连次数，不再重连");
                            //停止重连
                        }
                    }, RETRY_DELAY, TimeUnit.MILLISECONDS); //每隔5秒重连
                }
            });
            channel = future.channel();
            System.out.println(channel.id());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息 并接收返回值
     */
    public byte[] sendMsg(byte[] msg) throws InterruptedException {
        if (channel == null) {
            System.out.println("channel is null");
        }
        synchronized (this) {
            ByteBuf byteBuf = Unpooled.wrappedBuffer(msg);
            ChannelFuture sync = channel.writeAndFlush(byteBuf).sync();
            sync.addListener((ChannelFutureListener) channelFuture -> {
                if (channelFuture.isSuccess()) {
                    System.out.println("发送成功");
                } else {
                    System.out.println("发送失败");
                }
            });
            this.wait(8000);
            byte[] temp = res;
            res = null;
            return temp;
        }
    }

    public void login() {
        byte[] param = password.getBytes();
        byte[] msg = new byte[param.length + 1];
        msg[0] = 0x00000000;
        System.arraycopy(param, 0, msg, 1, param.length);
        try {
            byte[] bytes = sendMsg(msg);
            System.out.println("登录返回值：" + new String(bytes));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        //连接服务器
        connect();
        //登录
        System.out.println("开始登录");
        login();
    }
}
