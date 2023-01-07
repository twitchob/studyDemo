package com.example.designpattern.principle.isp;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 接口隔离原则
 * @since 2023/1/5 17:51
 */
public interface InterfaceSegregation1 {
    void operation1();
    void operation2();
    void operation3();
    void operation4();
    void operation5();

}

class D implements InterfaceSegregation1 {

    @Override
    public void operation1() {
        System.out.println("d实现了operation1");
    }

    @Override
    public void operation2() {
        System.out.println("d实现了operation2");

    }

    @Override
    public void operation3() {
        System.out.println("d实现了operation3");

    }

    @Override
    public void operation4() {
        System.out.println("d实现了operation4");

    }

    @Override
    public void operation5() {
        System.out.println("d实现了operation5");

    }
}
class B implements InterfaceSegregation1 {

    @Override
    public void operation1() {
        System.out.println("b实现了operation1");


    }

    @Override
    public void operation2() {
        System.out.println("b实现了operation2");

    }

    @Override
    public void operation3() {
        System.out.println("b实现了operation3");

    }

    @Override
    public void operation4() {
        System.out.println("b实现了operation4");

    }

    @Override
    public void operation5() {
        System.out.println("b实现了operation5");

    }
}

class A {
    public void depend1(InterfaceSegregation1 i) {
        i.operation1();
    } public void depend2(InterfaceSegregation1 i) {
        i.operation2();
    } public void depend3(InterfaceSegregation1 i) {
        i.operation3();
    }
}


