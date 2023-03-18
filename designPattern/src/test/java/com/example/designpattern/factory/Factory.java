package com.example.designpattern.factory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/3/14 14:27
 */
@SpringBootTest(classes = Factory.class)
public class Factory {


    @Test
    public void testFactory() {
        Factory1 factory1 = new FactoryA();
        Product product = factory1.getProduct();
        product.method();

        Factory1 factory2 = new FactoryB();
        Product product2 = factory2.getProduct();
        product2.method();

        //现在业务新增加一个产品C ,只需要新增产品C 继承产品接口,新增工厂C 继承抽象工厂,修改客户端代码
        //1.新增产品C 继承产品接口
        //2.新增工厂C 继承抽象工厂
        //3.修改客户端代码
        Factory1 factory3 = new FactoryC();
        Product product3 = factory3.getProduct();
        product3.method();


    }
}

/**
 * 产品接口
 */
interface Product {
    //产品类的公共方法
    void method();
}

class ProductA implements Product {

    @Override
    public void method() {
        System.out.println("生产产品A");
    }
}

class ProductB implements Product {

    @Override
    public void method() {
        System.out.println("生产产品B");
    }
}

/**
 * 新增产品C  不需要修改原来的的代码,只需要新增产品C 继承产品接口
 */
class ProductC implements Product {

    @Override
    public void method() {
        System.out.println("生产产品C");
    }
}


/**
 * 抽象工厂
 */
interface Factory1 {
    Product getProduct();
}

class FactoryA implements Factory1 {


    @Override
    public Product getProduct() {
        return new ProductA();
    }
}

class FactoryB implements Factory1 {

    @Override
    public Product getProduct() {
        return new ProductB();
    }
}

/**
 * 新增工厂C  不需要修改原来的的代码,只需要新增工厂C 继承抽象工厂
 */
class FactoryC implements Factory1 {

    @Override
    public Product getProduct() {
        return new ProductC();
    }
}
