package com.example.javatest.threadPool.thread;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 本软件遵循Apache开源协议，所有版权归神州安付信息科技有限公司所有
 * @since 2022/12/15 16:47
 */
public class ThreadDemo2 {
    public static void main(String[] args) throws InterruptedException {
        //开始时间
        long start = System.currentTimeMillis();
        //生成随机数对象
        Random random = new Random();
        //创建整形集合
        List<Integer> list = new ArrayList<>();
        //创建线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();

        //循环生成100000个随机数
        for (int i = 0; i < 100000; i++) {
            //多线程
            //            Thread thread = new Thread(() -> {
            //                list.add(random.nextInt());
            //            });
            //            thread.start();
            //            thread.join();

            //线程池

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            });
        }
        //关闭线程池
        executor.shutdown();

        //结束时间
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
        System.out.println("集合大小：" + list.size());
    }
}
