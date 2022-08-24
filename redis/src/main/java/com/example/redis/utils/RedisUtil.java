package com.example.redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/8/23 13:55
 */
public class RedisUtil {
    /**
     * 初始化连接池
     */
    private static JedisPool jedisPool = null;

    static {
        // 设置最大连接数
        JedisPoolConfig config = new JedisPoolConfig();
        // 可以创建30jedis实例
        config.setMaxTotal(30000);
        // 设置最大空闲连接数
        config.setMaxIdle(300);
        //等待可用连接的最大时间
        config.setMaxWaitMillis(10000);
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
        config.setTestOnBorrow(true);
        //

        jedisPool = new JedisPool(config, "192.168.3.112", 6379,1000,"123456");
    }

    /**
     * 获取Jedis实例
     * 每次用完要将连接返回给连接池 jedis.close();
     */
    public synchronized static Jedis getJedis() {
        System.out.println(jedisPool);
        if (jedisPool != null) {
            return jedisPool.getResource();
        } else {
            return null;
        }
    }

}
