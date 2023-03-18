package com.example.designpattern.builder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/3/14 16:03
 */
@SpringBootTest(classes = BuilderTest.class)
public class BuilderTest {



    @Test
    public void testBuilder() {
        //创建建造者
        ProductBuilder builder = new DefaultBuilder();
        //创建指挥者
        Director director = new Director(builder);
        //指挥者指挥建造者
        director.buildProduct("A", "B", "C");
        //获取产品
        Product product = builder.getProduct();
        System.out.println(product.getPartA());
        System.out.println(product.getPartB());
        System.out.println(product.getPartC());
    }
}

/**
 * 产品
 */
class Product {
    private String partA;
    private String partB;
    private String partC;

    //无参构造
    public Product() {
    }
    //有参构造
    public Product(String partA, String partB, String partC) {
        this.partA = partA;
        this.partB = partB;
        this.partC = partC;
    }



    public String getPartA() {
        return partA;
    }

    public void setPartA(String partA) {
        this.partA = partA;

    }

    public String getPartB() {
        return partB;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public String getPartC() {
        return partC;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }
}

/**
 * 抽象建造者
 */
interface ProductBuilder {
    void buildPartA(String partA);
    void buildPartB(String partB);
    void buildPartC(String partC);

    Product getProduct();
}

/**
 * 具体建造者 1
 */
class DefaultBuilder implements ProductBuilder {

    private String partA;
    private String partB;
    private String partC;

    @Override
    public void buildPartA(String partA) {
        this.partA = partA;
    }
    @Override
    public void buildPartB(String partB) {
        this.partB = partB;
    }
    @Override
    public void buildPartC(String partC) {
        this.partC = partC;
    }

    @Override
    public Product getProduct() {
        return new Product(this.partA, this.partB, this.partC);
    }
}


/**
 * 具体建造者 2
 */
class SpecialBuilder implements ProductBuilder {

    private String partA;
    private String partB;
    private String partC;

    @Override
    public void buildPartA(String partA) {
        this.partA = partA;
    }
    @Override
    public void buildPartB(String partB) {
        this.partB = partB;
    }
    @Override
    public void buildPartC(String partC) {
        this.partC = partC;
    }

    @Override
    public Product getProduct() {
        return new Product(this.partA, this.partB, this.partC);
    }
}






/**
 * 指挥者
 */
class Director {
    private ProductBuilder productBuilder;

    public Director(ProductBuilder productBuilder) {
        this.productBuilder = productBuilder;
    }

    public Product buildProduct(String partA, String partB, String partC) {
        productBuilder.buildPartA(partA);
        productBuilder.buildPartB(partB);
        productBuilder.buildPartC(partC);
        return productBuilder.getProduct();
    }
}

