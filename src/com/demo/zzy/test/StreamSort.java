package com.demo.zzy.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/4/12 14:35
 */
public class StreamSort {
    public static void main(String[] args) {
        //new cat
        Cat cat1 = new Cat("cat1", 1, "black");
        Cat cat2 = new Cat("cat2", 5, "white");
        Cat cat3 = new Cat("cat3", 3, "black");
        Cat cat4 = new Cat("cat4", 2, "white");
        Cat cat5 = new Cat("cat5", 4, "black");
        Cat cat6 = new Cat("cat6", 6, "white");
        Cat cat7 = new Cat("cat7", 1, "black");
        Cat cat8 = new Cat("", 2, "black");
        Cat cat9 = new Cat("", 2, "White");
        //new cat list
        List<Cat> cats = new ArrayList<Cat>(7);
        cats.add(cat1);
        cats.add(cat5);
        cats.add(cat6);
        cats.add(cat2);
        cats.add(cat3);
        cats.add(cat4);
        cats.add(cat7);
        cats.add(cat8);
        cats.add(cat9);
        System.out.println("排序前");
        System.out.println(cats);


        cats = cats.stream().sorted(Comparator.comparing(Cat::getAge).thenComparing(Cat::getName)).collect(Collectors.toList());

        System.out.println(cats);
        System.out.println("===========================");
        //sort
        cats = cats.stream().sorted(Comparator.comparing(Cat::getAge)).collect(Collectors.toList());
        System.out.println("年龄排序:");
        System.out.println(cats);

        //先以年龄排序，再反转，实现倒序
        cats = cats.stream().sorted(Comparator.comparing(Cat::getAge).reversed()).collect(Collectors.toList());
        System.out.println("年龄倒排:");
        System.out.println(cats);

        //直接年龄倒排
        cats = cats.stream().sorted(Comparator.comparing(Cat::getAge, Comparator.reverseOrder())).collect(Collectors.toList());
        System.out.println("年龄倒排:");
        System.out.println(cats);

        // 年龄升序,名字升序
        cats = cats.stream().sorted(Comparator.comparing(Cat::getAge).thenComparing(Cat::getName)).collect(Collectors.toList());
        System.out.println("年龄升序,名字升序:");
        System.out.println(cats);

        // 年龄降序,名字降序
        cats = cats.stream().sorted(Comparator.comparing(Cat::getAge, Comparator.reverseOrder()).thenComparing(Cat::getName,Comparator.reverseOrder())).collect(Collectors.toList());
        System.out.println("年龄降序,名字降序:");
        System.out.println(cats);

        // 年龄升序,名字降序
        cats = cats.stream().sorted(Comparator.comparing(Cat::getAge).thenComparing(Cat::getName,Comparator.reverseOrder())).collect(Collectors.toList());
        System.out.println("年龄升序,名字降序");
        System.out.println(cats);

        // 年龄降序,名字升序
        cats = cats.stream().sorted(Comparator.comparing(Cat::getAge, Comparator.reverseOrder()).thenComparing(Cat::getName)).collect(Collectors.toList());
        System.out.println("年龄降序,名字升序:");
        System.out.println(cats);



    }
}
