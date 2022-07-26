package com.example.annotation2;

import java.lang.reflect.Field;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description 反射获取注解属性值
 * @since 2022/7/21 19:08
 */
public class TestMain {

    public static void main(String[] args) throws  Exception{
        Student student = new Student();
        Class aClass = student.getClass();
        Field age = aClass.getDeclaredField("age");
        Fieldkuang annotation = age.getAnnotation(Fieldkuang.class);
        System.out.println(annotation.type());
    }
}
