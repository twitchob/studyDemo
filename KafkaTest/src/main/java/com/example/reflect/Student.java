package com.example.reflect;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/7/22 11:30
 */
public class Student {

    private String name;

    private int age;

    private boolean sex;

    public Student(){};

    public Student(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String eat (){
        return this.name+"使劲吃饭!";
    }
    public String sleep(){
        return "框框睡觉!";
    }

    public String kaoshi(String kemu) {
        return "今天考"+kemu;
    }
}
