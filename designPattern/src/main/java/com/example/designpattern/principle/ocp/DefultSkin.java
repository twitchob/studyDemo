package com.example.designpattern.principle.ocp;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 默认皮肤类
 * @since 2023/2/2 18:02
 */
public class DefultSkin extends AbstractSkin {
    @Override
    public void display() {
        System.out.println("默认皮肤");
    }
}

