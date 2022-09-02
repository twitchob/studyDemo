package com.demo.zzy.test;

import lombok.Data;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/9/1 10:31
 */
@Data
public class Student {
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;

    }
}
