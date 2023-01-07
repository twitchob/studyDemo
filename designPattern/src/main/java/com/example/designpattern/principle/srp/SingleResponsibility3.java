package com.example.designpattern.principle.srp;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * 没有对原来的类做大的修改，只是增加了方法
 *
 * @since 2023/1/5 17:35
 */
public class SingleResponsibility3 {
    public static void main(String[] args) {
        Vehicle2 vehicle2 = new Vehicle2();
        vehicle2.run("汽车");
        vehicle2.runAir("飞机");
        vehicle2.runWater("游艇");
    }
}

class Vehicle2 {
    public void run(String vehicle) {
        System.out.println(vehicle + " 在路上跑 ");
    }

    public void runAir(String vehicle) {
        System.out.println(vehicle + " 在天上飞 ");
    }

    public void runWater(String vehicle) {
        System.out.println(vehicle + " 在水里游 ");
    }

}
