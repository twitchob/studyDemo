package com.example.javatest.temp;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/8/24 18:29
 */
public class StringSub {
    public static void main(String[] args) {

        String sql = "SELECT id, name, age, CREATE_TIME\nFROM `db2`.`t2`";
        sql =  sql.replace(",", "");
        String[] split = sql.split("\\n| ");
        for (int i = 0; i < split.length; i++) {
          //  System.out.println(i + ":" + split[i]);
            if ("FROM".equals(split[i])) {
                System.out.println(i);
            }
        }


    }
}
