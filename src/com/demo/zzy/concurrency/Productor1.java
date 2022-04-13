package com.demo.zzy.concurrency;

import java.util.List;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/4/13 16:23
 */
public class Productor1 extends Thread {
    public List<String> list;

    public Productor1(List<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                if (list.size() == 10) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.add("生产者正在生产!");
                list.notify();
            }
        }
    }
}


