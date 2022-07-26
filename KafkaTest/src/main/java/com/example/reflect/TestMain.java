package com.example.reflect;

import java.lang.reflect.Method;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/7/22 11:36
 */
public class TestMain {

    public static void main(String[] args) throws Exception {
        Student s1 = new Student("郭若男", 20, true);
        Class<? extends Student> aClass = s1.getClass();
        Method eat = aClass.getDeclaredMethod("eat");
        System.out.println(eat.invoke(s1));

        Method sleep = aClass.getDeclaredMethod("sleep");
        System.out.println(sleep.invoke(s1));
        Method kaoshi = aClass.getDeclaredMethod("kaoshi",String.class);


        System.out.println(kaoshi.invoke(s1,"yuwen") );


    }
}
