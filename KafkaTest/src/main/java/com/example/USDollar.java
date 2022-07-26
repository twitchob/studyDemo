package com.example;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/7/26 9:47
 */
public class USDollar {
    public static void main(String[] args) {

        int i = "123".indexOf(1);
        System.out.println(i);
        System.out.println("123".length());

    }

    public String getUSDolalar(String money) {
        int i = money.indexOf(".");
        if (i == -1) {
            return "有小数点的老子不会写";
        }
        int intMoney = Integer.parseInt(money);
        int length = money.length();




        return "";
    }
}
