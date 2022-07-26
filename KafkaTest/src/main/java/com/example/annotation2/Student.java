package com.example.annotation2;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/7/21 19:03
 */


public class Student {
    @Fieldkuang(columnName = "id", type = "int",length = 10)
    public int id;
    @Fieldkuang(columnName = "age", type = "int",length = 10)
    public int age;
    @Fieldkuang(columnName = "name", type = "String",length = 100)
    public String name;


}
