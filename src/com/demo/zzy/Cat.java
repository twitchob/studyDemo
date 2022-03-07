package com.demo.zzy;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Cat {
    private String name;
    private int age;
    private String color;

    public Cat(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }

    //重写hashCode方法 equals方法
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + age;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Cat other = (Cat) obj;
        if (age != other.age) {
            return false;
        }
        if (color == null) {
            if (other.color != null) {
                return false;
            }
        } else if (!color.equals(other.color)) {
            return false;
        }
        if (name == null) {
            return other.name == null;
        } else {
            return name.equals(other.name);
        }
    }

    //main方法
    public static void main(String[] args) {
        Cat cat1 = new Cat("Tom", 2, "black");
        Cat cat2 = new Cat("Tom", 2, "black");
        Cat cat3 = new Cat("Tom3", 3, "black3");
        Cat cat4 = new Cat("Tom4", 4, "black4");
        Cat cat5 = new Cat("Tom4", 4, "black4");

        ArrayList<Cat> cats = new ArrayList<>(5);
        cats.add(cat1);
        cats.add(cat2);
        cats.add(cat3);
        cats.add(cat4);
        cats.add(cat5);

      /*  //stream去重
        System.out.println("stream去重");
        cats.stream().distinct().forEach(System.out::println);

        //stream给对象按照age排序
        System.out.println("排序==========================");
        cats.stream().sorted((o1, o2) -> o2.getAge() - o1.getAge()).forEach(System.out::println);

        //去重并且排序
        System.out.println("去重并且排序==========================");
        cats.stream().distinct()
                .sorted((o1, o2) -> o2.getAge() - o1.getAge()).forEach(System.out::println);

        //每个对象的age加1
        System.out.println("每个对象的age加1==========================");
        cats.stream().peek(cat -> cat.setAge(cat.getAge() + 1)).forEach(System.out::println);

        //每个对象，age为奇数乘以2，偶数加1
        System.out.println("每个对象，age为奇数乘以2，偶数加1==========================");
        cats.stream().peek(cat -> cat.setAge(cat.getAge() % 2 == 0 ? cat.getAge() + 1 : cat.getAge() * 2))
                .forEach(System.out::println);

        //如果名字为Tom3，则age不变，其他每个对象，age为奇数乘以2，偶数加1,
        System.out.println("如果名字为Tom3，则age不变，其他每个对象，age为奇数乘以2，偶数加1==========================");
        cats.stream().peek(cat -> {
            if ("Tom3".equals(cat.getName())) {
                cat.setAge(cat.getAge());
            } else {
                cat.setAge(cat.getAge() % 2 == 0 ? cat.getAge() + 1 : cat.getAge() * 2);
            }
        }).forEach(System.out::println);*/

     /*   //将对象转换为字符串
        System.out.println("将对象转换为字符串==========================");
        cats.stream().map(Cat::toString).forEach(System.out::println);*/


       /* //将cats转化为map
        System.out.println("将cats转化为map==========================");
        Map<String, Cat> map = cats.stream().distinct().collect(Collectors.toMap(Cat::getName, cat -> cat));
        System.out.println(map);*/

        //将cats转化为map，并且按照age排序
        System.out.println("将cats转化为map，并且按照age排序==========================");
        Map<String, Object> map2 = cats.stream()
                .distinct()
                .sorted(Comparator.comparingInt(Cat::getAge))
                .collect(Collectors.toMap(Cat::getName, Cat::getAge, (oldValue, newValue) -> oldValue));
        System.out.println(map2);




    }
}