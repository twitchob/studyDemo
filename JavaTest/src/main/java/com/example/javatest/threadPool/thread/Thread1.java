package com.example.javatest.threadPool.thread;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 本软件遵循Apache开源协议，所有版权归神州安付信息科技有限公司所有
 * @since 2022/12/15 16:27
 */
public class Thread1 extends Thread {
    private String name;


    //构造方法
    public Thread1(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println("线程——" + name + "正在执行");
    }
}

