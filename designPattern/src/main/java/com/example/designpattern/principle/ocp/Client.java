package com.example.designpattern.principle.ocp;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/2/2 18:04
 */
public class Client {
    public static void main(String[] args) {

        //搜狗输入法对象
        SououInput sououInput = new SououInput();

        //默认皮肤
        sououInput.setSkin(new DefultSkin());
        sououInput.display();


        //黑马皮肤
        sououInput.setSkin(new HeimaSkin());
        sououInput.display();
    }
}
