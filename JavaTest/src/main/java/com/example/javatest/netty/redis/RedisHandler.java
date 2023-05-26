package com.example.javatest.netty.redis;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/4/23 18:29
 */
public class RedisHandler extends ChannelInboundHandlerAdapter {
    // 连接建立后立刻调用
    // 回车换行
    final byte[] LINE = {13,10};
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = ctx.alloc().buffer();
        // 三个元素
        buf.writeBytes("*3".getBytes());
        buf.writeBytes(LINE);
        // 长度
        buf.writeBytes("$3".getBytes());
        buf.writeBytes(LINE);
        // 命令
        buf.writeBytes("set".getBytes());
        buf.writeBytes(LINE);
        buf.writeBytes("$4".getBytes());
        buf.writeBytes(LINE);
        buf.writeBytes("name".getBytes());
        buf.writeBytes(LINE);
        buf.writeBytes("$8".getBytes());
        buf.writeBytes(LINE);
        buf.writeBytes("zhangsan".getBytes());
        buf.writeBytes(LINE);
        ctx.writeAndFlush(buf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        ByteBuf buf = (ByteBuf)msg;
        System.out.println("返回数据:\n"+buf.toString(Charset.defaultCharset()));
    }
}
