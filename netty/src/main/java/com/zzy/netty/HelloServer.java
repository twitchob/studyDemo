package com.zzy.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/4/13 11:35
 */
public class HelloServer {
    public static void main(String[] args) {
        //1.启动器,负责组装netty组件,启动服务器
        new ServerBootstrap()
                //2.指定NIO模式
                .group(new NioEventLoopGroup())
                //3.指定IO模型
                .channel(NioServerSocketChannel.class)
                //4.IO处理逻辑
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new StringDecoder());
                        channel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                //5.绑定端口
                .bind(8000);
    }
}
