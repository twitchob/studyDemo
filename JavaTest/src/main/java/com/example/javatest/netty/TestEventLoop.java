package com.example.javatest.netty;

import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/4/23 16:10
 */
public class TestEventLoop {
    public static void main(String[] args) {
        NioEventLoopGroup group = new NioEventLoopGroup(2);
        //执行普通任务
        group.next().submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("执行普通任务");
        });
        System.out.println("main线程执行完毕");
    }
}
