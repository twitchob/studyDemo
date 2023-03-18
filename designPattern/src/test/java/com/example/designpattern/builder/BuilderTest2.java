package com.example.designpattern.builder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/3/14 16:23
 */
@SpringBootTest(classes = BuilderTest2.class)
public class BuilderTest2 {
    @Test
    void test(){


        Product2 build = new Product2.Builder2().setPartA("A").setPartB("B").build();


    }



}



class Product2{
    String partA;
    String partB;
    String partC;

    //无参构造
    public Product2() {
    }
    //
    public Product2(String partA, String partB, String partC) {
        this.partA = partA;
        this.partB = partB;
        this.partC = partC;
    }

    public static class Builder2{
        String partA;
        String partB;
        String partC;

        Builder2 setPartA(String partA) {
            this.partA = partA;
            return this;
        }
        Builder2 setPartB(String partB) {
            this.partB = partB;
            return this;
        }
        Builder2 setPartC(String partC) {
            this.partC = partC;
            return this;
        }
        Product2 build(){
            return new Product2(partA,partB,partC);
        }
    }
}
