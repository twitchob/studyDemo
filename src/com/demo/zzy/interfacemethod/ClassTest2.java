package com.demo.zzy.interfacemethod;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/8/11 15:26
 */
public class ClassTest2 implements InterfaceTest{


    @Override
    public String method() {
        System.out.println("2");
        return "class2实现方法";
    }
    InterfaceTest interfaceTest;
    public static void main(String[] args) {
    }
}
