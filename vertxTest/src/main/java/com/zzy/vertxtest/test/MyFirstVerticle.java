package com.zzy.vertxtest.test;

import io.vertx.core.AbstractVerticle;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/8/19 15:11
 */
public class MyFirstVerticle extends AbstractVerticle {
    @Override
    public void start() {
        System.out.println("开始");
        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello World!");
        }).listen(8080);
    }
}
