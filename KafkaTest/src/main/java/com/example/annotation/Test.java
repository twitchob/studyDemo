package com.example.annotation;

import com.example.annotation2.Fieldkuang;

import java.lang.reflect.Field;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/7/21 18:56
 */
public class Test {
    public static void main(String[] args) throws Exception{
        SuperMan superMan = new SuperMan();
        Class<? extends SuperMan> aClass = superMan.getClass();
        boolean annotationPresent = aClass.isAnnotationPresent(Person.class);
        System.out.println(annotationPresent);
        Field name = aClass.getDeclaredField("name");
        Fieldkuang annotation = name.getAnnotation(Fieldkuang.class);
        System.out.println(annotation);

    }
}
