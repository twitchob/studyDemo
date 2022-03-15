package com.demo.zzy.test;

import java.util.ArrayList;

/**
 * @author 张忠源
 * @description LamdbaTest
 * @since 2022/3/15 12:00
 */
public class LamdbaTest {
    //循环10次创建cat对象，设置名字为cat1-cat10
    public static void main(String[] args) {
        ArrayList<Cat > cats = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Cat cat = new Cat();
            cat.setName("cat" + (i + 1));
            System.out.println(cat.getName());
            cats.add(cat);
        }

        System.out.println("-----------------");
        //使用Lamdba表达式遍历
        cats.forEach(cat -> {
            cat.setAge(100);
            System.out.println(cat.getAge());
        });

    }
}
