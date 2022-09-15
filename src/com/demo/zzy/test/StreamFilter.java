package com.demo.zzy.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/4/8 11:52
 */
public class StreamFilter {

    public static void main(String[] args) {
      /*  List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        System.out.println(list);

        list = list.stream().filter(s -> !s.equals("1")).collect(Collectors.toList());
        System.out.println(list);
        */


        Student zzy = new Student("zzy", 26);
        Student grn = new Student("grn", 22);
        Student a = new Student("ttt", 22);
        Student b = new Student("gxxrn", 22);

        List<Student> ss = new ArrayList<>();
        ss.add(zzy);
        ss.add(grn);
        ss.add(a);
        ss.add(b);
        List<String> list = new ArrayList<>();
        list.add("zzy");
        list.add("grn");


        List<String> collect = ss.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println(collect);
        List<Student> collect1 = ss.stream().filter(s -> list.contains(s.getName())).collect(Collectors.toList());
        System.out.println(collect1);

        System.out.println("===============================================================");


        List<String> strs = new ArrayList<>();
        strs.add("a_abc");
        strs.add("b_abc");
        strs.add("v_abc");
        strs.add("c_abc");
        strs.add("c_ac");

        List<String> abc = strs.stream().filter(s -> !s.contains("_abc")).collect(Collectors.toList());
        System.out.println(abc);
    }
}
