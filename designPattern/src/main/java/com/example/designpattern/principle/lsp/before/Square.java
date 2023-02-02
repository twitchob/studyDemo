package com.example.designpattern.principle.lsp.before;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description  正方形
 * @since 2023/2/2 18:18
 */
public class Square extends Rectangle{
    @Override
    //setLength
    public void setLength(double length) {
        super.setLength(length);
        super.setWidth(length);
    }

    //setWidth
    @Override
    public void setWidth(double width) {
        super.setLength(width);
        super.setWidth(width);
    }
}
