package com.demo.zzy.interfacemethod;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/8/11 15:25
 */
public class ClassTest1 implements InterfaceTest {

    @Override
    public String method()  {
        System.out.println("1");
        return "class1实现方法";
    }
}
