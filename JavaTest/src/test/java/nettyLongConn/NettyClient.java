package nettyLongConn;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.Setter;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/5/26 9:48
 */
public class NettyClient {


    private final String host;
    private final int port;
    private final String password;


    private EventLoopGroup group;
    private Channel channel;
    private ChannelFuture future;

    private int MAX_RETRY = 3; // 最大重试次数
    private int RETRY_DELAY = 5000; // 重试间隔时间（毫秒）
    private int retryCount = 0; // 当前重试次


    @Setter
    private NettyClientHandler nettyClientHandler;

    public NettyClient(String host, int port, String password) {
        this.host = host;
        this.port = port;
        this.password = password;
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
            future = bootstrap.connect(host, port).sync();
            channel = future.channel();

            //断线重连
//            future.addListener((ChannelFutureListener) channelFuture -> {
//                if (channelFuture.isSuccess()) {
//                    channel = channelFuture.channel();
//                    System.out.println("连接成功");
//                } else {
//                    System.out.println("连接失败");
//                    channelFuture.channel().eventLoop().schedule(() -> {
//                        System.out.println("开始重连");
//                        connect();
//                        //重连次数
//                        retryCount++;
//                        if (retryCount >= MAX_RETRY) {
//                            System.out.println("重连次数超过最大重连次数，不再重连");
//                            //停止重连
//                        }
//                    }, RETRY_DELAY, TimeUnit.MILLISECONDS); //每隔5秒重连
//                }
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息 并接收返回值
     */
    public void sendMsg(byte[] msg) {
        if (channel == null) {
            System.out.println("channel is null");
        }
        channel.writeAndFlush(msg);

    }

    public void receiveMsg(String string) {
        System.out.println("receiveMsg:" + string);
    }
}
