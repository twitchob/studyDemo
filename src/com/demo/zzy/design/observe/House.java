package com.demo.zzy.design.observe;

import java.util.Observable;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 观察者模式，被观察者继承Observable类(java.util包下），观察者实现Observer接口(java.util包下)
 * @since 2022/8/17 14:34
 */
public class House extends Observable {
    private float price;

    public House(float price) {
        this.price = price;
    }

    /**
     * get
     */
    public float getPrice() {
        return price;
    }

    /**
     * set
     */
    public void setPrice(float price) {
        super.setChanged();
        super.notifyObservers(price);
        this.price = price;
    }

    @Override
    public String toString() {
        return "House{" +
                "price=" + price +
                '}';
    }
}
