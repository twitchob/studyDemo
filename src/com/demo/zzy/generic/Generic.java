package com.demo.zzy.generic;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/8/12 11:22
 */
public class Generic {
    public static void main(String[] args) {
        int h = 512;
        int i = h >>> 3;//无符号右移
        int j = h >> 3;//右移
        System.out.println(i);
        System.out.println(j);
    }
}


