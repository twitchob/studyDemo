package com.demo.zzy.test;

import java.util.HashMap;

/**
 * @author 张忠源
 * @description MapGetNullTest
 * @since 2022/3/15 19:19
 */
public class MapGetNullTest {
    /**
     * map.get(key) 如果map中没有该key，则返回null
     *
     */
    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "");
        map.put("3", null);
        System.out.println(map.get("1"));// "1"
        System.out.println(map.get("2"));// "
        System.out.println(map.get("3"));// null
        System.out.println(map.get("4"));// null

    }
}
