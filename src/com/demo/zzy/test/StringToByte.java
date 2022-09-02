package com.demo.zzy.test;

import java.nio.charset.StandardCharsets;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/9/1 14:36
 */
public class StringToByte {
    public static void main(String[] args) {
        String s = "3�\f\u001A�y�}�sK�p���";
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        System.out.println(bytes);
    }
}
