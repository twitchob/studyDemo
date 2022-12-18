package com.example.javatest.threadPool.thread;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 本软件遵循Apache开源协议，所有版权归神州安付信息科技有限公司所有
 * @since 2022/12/15 16:26
 */
public class ThreadDemo {
    public static void main(String[] args) {
        //创建线程对象
        Thread1 thread1 = new Thread1("线程1");
        Thread1 thread2 = new Thread1("线程2");
        Thread1 thread3 = new Thread1("线程3");
        //启动线程
        thread1.start();
        thread2.start();
        thread3.start();


        //run
//        thread1.run();
//        thread2.run();
//        thread3.run();
    }
}
