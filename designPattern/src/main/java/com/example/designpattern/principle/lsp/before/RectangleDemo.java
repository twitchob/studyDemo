package com.example.designpattern.principle.lsp.before;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/2/2 18:18
 */
public class RectangleDemo {

    //resize方法
    public static void resize(Rectangle rectangle) {
        //宽小于长  拓宽
        while (rectangle.getWidth() <= rectangle.getLength()) {
            rectangle.setWidth(rectangle.getWidth() + 1);
        }
    }
    //打印长和宽
    public static void printLengthAndWidth(Rectangle rectangle) {
        System.out.println(rectangle.getLength());
        System.out.println(rectangle.getWidth());
    }

    public static void main(String[] args) {
        //创建长方形
        Rectangle rectangle = new Rectangle();

        //设置长和宽
        rectangle.setWidth(10);
        rectangle.setLength(20);

        //拓宽 打印长和宽
        resize(rectangle);
        printLengthAndWidth(rectangle);


        System.out.println("====================================");

        //创建正方形
        Square square = new Square();
        //设置
        square.setLength(10);
        //拓宽 打印长和宽
        resize(square);
        printLengthAndWidth(square);
    }
}
