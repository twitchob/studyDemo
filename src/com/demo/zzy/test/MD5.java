package com.demo.zzy.test;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/8/9 14:22
 */
public class MD5 {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        Integer a = 1;
        int b = 1;
        System.out.println(a);
        System.out.println(((Object) b).toString());
        System.out.println("====================");

        List<Object> objs = new ArrayList<>(2);
        Integer inte = 1;
        Integer inte2 = 2;
        String s = "3";
        objs.add(inte);
        objs.add(inte2);
        objs.add(s);

        System.out.println(objs.get(0) instanceof Integer);
        System.out.println(objs.get(2) instanceof String);
        objs.forEach(System.out::println);
    }


}
