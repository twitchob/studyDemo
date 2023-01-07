package com.example.designpattern.principle.srp;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description  单一职责原则
 * @since 2023/1/5 17:24
 */
public class SingleResponsibility1 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("摩托");
        vehicle.run("汽车");
        vehicle.run("飞机");
    }
}

/**
 * 交通工具类 方式1
 *
 */
class Vehicle {
    public void run(String vehicle) {
        System.out.println(vehicle+"is running ");
    }

}