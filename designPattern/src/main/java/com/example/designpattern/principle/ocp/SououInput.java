package com.example.designpattern.principle.ocp;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description  搜狗输入法
 * @since 2023/2/2 18:03
 */
public class SououInput  {
    private AbstractSkin skin;

    public void setSkin(AbstractSkin skin) {
        this.skin = skin;
    }

    public void display() {
        skin.display();
    }
}
