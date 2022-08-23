package com.example.redis.test;

import redis.clients.jedis.Jedis;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/8/23 11:19
 */
public class TestRedis {
    public static void main(String[] args) {
        // 1.创建Jedis连接
        String host = "192.168.3.112";
        int port = 6379;
        Jedis jedis = new Jedis(host, port);
        jedis.auth("123456");
        // 2.检测连通性
        String result = jedis.ping();
        System.out.println("result=" + result);
        // 3.关闭Jedis连接
        jedis.close();
    }
}
