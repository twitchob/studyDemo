package com.example.redis.test;

import com.example.redis.utils.RedisUtil;
import redis.clients.jedis.Jedis;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/8/23 13:57
 */
public class TestRedisPool {


    public static void main(String[] args) {

        try {
            Jedis jedis = RedisUtil.getJedis();
            assert jedis != null;
            String hget = jedis.hget("`db2`.`t1`", "s");
            System.out.println(hget);
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
