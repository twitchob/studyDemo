package com.zzy.vertxtest.test;

import io.vertx.core.Vertx;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/8/19 15:11
 */
public class Main {
    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(MyFirstVerticle.class.getName());
    }
}
