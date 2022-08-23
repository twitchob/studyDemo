package com.example.redis.test;

import com.example.redis.utils.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/8/23 13:57
 */
public class TestRedisPool {

    public static void main(String[] args) {





        //连接redis
        try {
            Jedis jedis = RedisUtil.getJedis();
            assert jedis != null;
            jedis.sadd("url","www.baidu.com");
            Set<String> url = jedis.smembers("url");
            System.out.println(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
