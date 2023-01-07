package com.example.designpattern.principle.srp;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description  改动比较大，需要新的类
 * @since 2023/1/5 17:24
 */
public class SingleResponsibility2 {
    public static void main(String[] args) {
        RoadVehical roadVehical = new RoadVehical();
        roadVehical.run("汽车");
        AirVehical airVehical = new AirVehical();
        airVehical.run("飞机");
    }
}

class RoadVehical {
    public void run(String vehical) {
        System.out.println(vehical+"在路上跑");
    }
}
class AirVehical {
    public void run(String vehical) {
        System.out.println(vehical+"天上飞");
    }
}
