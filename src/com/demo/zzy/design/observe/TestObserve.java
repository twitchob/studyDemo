package com.demo.zzy.design.observe;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/8/17 15:14
 */
public class TestObserve {
    public static void main(String[] args) {
        //被观察者  房子
        House house = new House(8000.0F);

        //观察者  买房的人
        People zzy = new People("zzy");
        People grn = new People("grn");
        People gxb = new People("gxb");
        People zxn = new People("zxn");

        //添加买房子的到售楼处
        house.addObserver(zzy);
        house.addObserver(grn);
        house.addObserver(gxb);
        house.addObserver(zxn);


        System.out.println(house);
        house.setPrice(10000.0F);
        System.out.println(house);
    }
}
