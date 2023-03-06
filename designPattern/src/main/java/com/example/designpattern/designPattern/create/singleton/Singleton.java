package com.example.designpattern.designPattern.create.singleton;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 单例模式 饿汉式：类加载时就初始化
 *
 * @since 2023/2/2 18:47
 */
public class Singleton {
    //成员变量位置创建静态对象
    private static Singleton singleton = new Singleton();

    // 私有化构造器
    private Singleton() {
    }

    // 提供一个公有的静态方法，返回实例对象
    public static Singleton getInstance() {
        return singleton;
    }
}
