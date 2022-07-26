package com.example.useannotation;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/7/22 10:26
 */
public class TestMain2 {
    public static void main(String[] args) {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            System.out.println(cause);
            System.out.println("catch yes!");
        }
    }
}
