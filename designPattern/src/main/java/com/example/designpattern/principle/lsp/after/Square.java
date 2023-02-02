package com.example.designpattern.principle.lsp.after;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 正方形
 * @since 2023/2/2 18:31
 */
public class Square implements Quadrilateral {
    private double sideLength;

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getLength() {
        return sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

}
