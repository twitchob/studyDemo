package com.example.designpattern.principle.lsp.after;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/2/2 18:34
 */
public class RectangleDemo {
    public static void main(String[] args) {
        //create Rectangle
        Rectangle rectangle = new Rectangle();
        //set Length and Width
        rectangle.setLength(20);
        rectangle.setWidth(10);
        //resize Rectangle
        resize(rectangle);
        //printLengthAndWidth
        printLengthAndWidth(rectangle);

        System.out.println("====================================");

    }

    //resize Rectangle
    public static void resize(Rectangle rectangle) {
        while (rectangle.getWidth() <= rectangle.getLength()) {
            rectangle.setWidth(rectangle.getWidth() + 1);
        }
    }


    //printLengthAndWidth
    public static void printLengthAndWidth(Quadrilateral quadrilateral) {
        System.out.println(quadrilateral.getLength());
        System.out.println(quadrilateral.getWidth());
    }
}
