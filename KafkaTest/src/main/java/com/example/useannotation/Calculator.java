package com.example.useannotation;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/7/22 10:04
 */
public class Calculator {

    @Check
    public void add() {
        System.out.println(1 + 1);
    }

    @Check
    public void sub() {
        System.out.println(2 - 1);
    }

    @Check
    public void mul() {
        System.out.println(1 * 2);
    }

    @Check
    public void division() {
        System.out.println(1 / 0);
    }
}
