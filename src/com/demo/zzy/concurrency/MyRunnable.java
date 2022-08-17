package com.demo.zzy.concurrency;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/4/13 16:51
 */
public class MyRunnable  {
    public static void main(String[] args) {
        //hashmap，同一个key，后放入的会覆盖之前的数据
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("1","2");
        System.out.println(map.get("1"));
    }
}
