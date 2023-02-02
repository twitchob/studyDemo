package com.example.designpattern.principle.lsp.after;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/2/2 18:32
 */
public class Rectangle implements Quadrilateral {
    private double length;
    private double width;

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

}
