package com.demo.zzy.test;

public class Test {
    public static void main(String[] args) {
        String str = "0.123456";
        System.out.println("0-2: "+str.substring(0,2));
        System.out.println("1-2: "+str.substring(1,2));
        System.out.println("1-3: "+str.substring(1,3));
        System.out.println("0-3: "+str.substring(0,3));


        System.out.println("0-1: "+str.substring(0,1));
        System.out.println("0-1: "+str.charAt(0));
        System.out.println("1-1: "+str.substring(1,1));

        System.out.println(str.startsWith("0"));

        str = str.substring(0, 2);
        System.out.println(str);
    }
}
