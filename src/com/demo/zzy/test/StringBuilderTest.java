package com.demo.zzy.test;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/9/5 10:49
 */
public class StringBuilderTest {


    public static void main(String[] args) {
        StringBuilder s = new StringBuilder("1");
        s.append("2");
        s.append("2");
        s.append("2");
        s.append("2");
        s.append("2");
        s.append("2");
        test(s);
        System.out.println(s);
        s.setLength(0);
        System.out.println(s);
        s.append("nnnnnnnnnnnnn");
        System.out.println(s);
        System.out.println("===============================");
        String format = String.format("SELECT\n\tCOLUMN_NAME \nFROM\n\tUSER_TAB_COLUMNS \nWHERE\n\tTABLE_NAME = ? ");
        System.out.println(format);
    }

    public static void test(StringBuilder s) {
        s.append("365145614651465165");
    }
}
