package nettyLongConn;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/5/26 9:59
 */
public class NettyClientHandler extends SimpleChannelInboundHandler {

    private final NettyClient nettyClient;
    private int heartNumber;

    public NettyClientHandler(NettyClient nettyClient) {
        this.nettyClient = nettyClient;
        this.nettyClient.setNettyClientHandler(this);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    /**
     * 通道连接时触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //连接成功后，发送登录信息
        super.channelActive(ctx);
    }

    /**
     * 读取服务端的消息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf msg1 = (ByteBuf) msg;
        byte[] bytes = new byte[msg1.readableBytes()];
        msg1.readBytes(bytes);
        nettyClient.setRes(bytes);
        synchronized (nettyClient) {
            nettyClient.notify();
        }
    }


    /**
     * 发生异常时触发 断线重连
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        //使用过程中断线重连
        final EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(nettyClient::connect, 1, TimeUnit.SECONDS);
        ctx.fireChannelInactive();
    }

    /**
     * 心跳检测
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE)) {
                System.out.println("READER_IDLE");
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                /**发送心跳,保持长连接*/
                byte[] buff = {'V', 'Z', 1, 0, 0, 0, 0, 0};
                String cmd = new String(buff);
                ctx.channel().writeAndFlush(cmd);

            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                System.out.println("ALL_IDLE");
            }
        }
        super.userEventTriggered(ctx, evt);
    }


}
