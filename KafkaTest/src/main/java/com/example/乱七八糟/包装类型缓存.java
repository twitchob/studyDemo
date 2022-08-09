package com.example.乱七八糟;

import java.util.HashMap;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/7/26 16:01
 */
public class 包装类型缓存 {
    public static void main(String[] args) {


        /**
         * int 127以下会缓存在虚拟机栈当中,所以==  比较地址是一致的,但是以上的数据就是存在堆中,地址就不同
         *
         *
         */
        Integer i1 = 127;
        Integer i2 = 127;
        Integer i3 = 200;
        Integer i4 = 200;
        System.out.println(i1 == i2);  //输出true
        System.out.println(i3 == i4);  //输出false

       /* new HashMap<>();

        int i = 4 >> 1;
        System.out.println(i);*/
    }
}
