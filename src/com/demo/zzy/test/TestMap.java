package com.demo.zzy.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/4/14 15:37
 */
public class TestMap {
    public static void main(String[] args) {
        Cat cat1 = new Cat("cat1", 1, "black");
        Cat cat2 = new Cat("cat2", 2, "white");
        Cat cat3 = new Cat("cat3", 3, "black");
        List<Cat> cats = new ArrayList<>(3);
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);
        Map<String, Object> map = new HashMap<>(3);
        map.put("cats", cats);

        /**
         * 不要在控制层过滤，这种转换会触发类型转换异常
         */
        List<Cat> cats1 = (List<Cat>) map.get("cats");
        System.out.println(cats1.get(0).getName());

    }
}
