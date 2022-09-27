package com.demo.zzy.test;

/**
 * @Author: 张忠源
 * @Description: +TODO
 * @DateTime: 2022/3/10 11:21
 **/

public class Father {
    private String name;
    private int age;

    //构造方法
    public Father(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Father() {
    }

    //setter and getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Father{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
