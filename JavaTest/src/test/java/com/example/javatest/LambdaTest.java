package com.example.javatest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LambdaTest
 * @Description TODO
 * @Author 张忠源  zhangzhongyuan@szanfu.cn
 * @Date DATE{TIME}
 */
@SpringBootTest
public class LambdaTest {

    /**
     * 包含两个方法的HelloWorld接口
     */
    interface helloworld {
        void greet();

        void greetSomeone(String someone);
    }

    /**
     * sayHello方法
     */
    public void sayHello() {
    //局部类实现
        class EnglishGreeting implements helloworld {
            String name = "world";

            @Override
            public void greet() {
                greetSomeone("world");
            }

            @Override
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        }
        helloworld englishGreeting = new EnglishGreeting();
        englishGreeting.greet();


        //匿名类实现
        helloworld frenchGreeting = new helloworld() {
            String name = "tout le monde";

            @Override
            public void greet() {
                greetSomeone("tout le monde");
            }

            @Override
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Salut " + name);
            }
        };
        frenchGreeting.greetSomeone("Fred");
        frenchGreeting.greet();

        //匿名类实现
        helloworld spanishGreeting = new helloworld() {
            String name = "mundo";

            @Override
            public void greet() {
                greetSomeone("mundo");
            }

            @Override
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hola, " + name);
            }
        };
        spanishGreeting.greet();
    }
    //main
    public static void main(String[] args) {
        LambdaTest lambdaTest = new LambdaTest();
        lambdaTest.sayHello();
    }
}
