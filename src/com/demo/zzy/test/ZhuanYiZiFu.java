package com.demo.zzy.test;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/8/24 11:12
 */
public class ZhuanYiZiFu {
    public static void main(String[] args) {

        String s = "INSERT INTO `db2`.`t2`\nVALUES (?, ?, ?, ?)";
        String[] split = s.split("\\n| ");
        for (int i = 0; i < split.length; i++) {
            System.out.println(i+":"+split[i]);
        }
    }
}
