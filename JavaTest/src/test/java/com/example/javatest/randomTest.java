package com.example.javatest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/3/2 14:30
 */
@SpringBootTest
public class randomTest {
    @BeforeAll
    public static void beforeAll() {
        System.out.println("===============Start================");
    }


    @AfterAll
    public static void afterAll() {
        System.out.println("===============End================");
    }


    @Test
    public void test() {
        for (int j = 0; j < 100; j++) {
            //随机数 生成0-999999的随机数 生成1000000个 统计1开头的概率
            int count = 0;
            for (int i = 0; i < 1000000; i++) {
                int random = (int) (Math.random() * 10000000);
                //判断是否是1开头
                if(String.valueOf(random).startsWith("1")){
                    count++;
                }
            }
            System.out.println("1开头的概率 = " + count / 1000000.0);
        }
    }

    @Test
    public void test2() {
        //1-1000000 之间以1开头的数字的个数
        int count = 0;
        for (int i = 1; i <= 1000000; i++) {
            if(String.valueOf(i).startsWith("1")){
                count++;
            }
        }
        System.out.println("1-1000000 之间以1开头的数字的个数 = " + count);


    }
    @Test
    public void test3() {
        //1-100随机数
        for (int i = 0; i < 100; i++) {
            int random = (int) (Math.random() * 100);
            System.out.println("random = " + random);
        }

    }


}
