package com.demo.zzy.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description 测试 对象放入list，把list放入map中，map get（key）时，能否直接返回 改对象为泛型的集合
 * @since 2022/4/13 10:15
 */
public class MapGetKeyIsAnList {
    public static void main(String[] args) {
        //new cat
        Cat cat1 = new Cat("cat1", 1, "black");
        Cat cat2 = new Cat("cat2", 2, "white");
        Cat cat3 = new Cat("cat3", 3, "yellow");
        //new list
        List<Cat> list = new ArrayList<Cat>();
        //add cat to list
        list.add(cat1);
        list.add(cat2);
        list.add(cat3);
        //new map
        Map<String,Object> map = new HashMap<String, Object>(16);
        //put list to map
        map.put("cat", list);
        //get cat from map
        List<Cat> catList = (List<Cat>) map.get("cat");

        for (Cat cat : catList) {
            System.out.println(cat.getName()+" "+cat.getAge()+" "+cat.getColor());
        }




    }
}
