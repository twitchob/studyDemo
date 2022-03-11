package com.demo.zzy.test;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 张忠源
 * @Description: TODO
 * @DateTime: 2022/3/10 11:23
 **/
public class Demo {
    public static String method(List<Father> fatherList) {
        for (Father father : fatherList) {
            System.out.println(father.getName());
        }
        return "";

    }

    public static String method2(Father father) {
        System.out.println(father.getName());
        return "";
    }
    public static void main(String[] args) {
        Son son = new Son();
        son.setName("张忠源");
        son.setAge(18);
        List<Son> sons = new ArrayList<>();
        sons.add(son);
      // method(sons);   不可以
        method2(son);    //可以
    }

}

