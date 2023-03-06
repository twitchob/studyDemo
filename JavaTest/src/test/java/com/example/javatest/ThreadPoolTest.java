package com.example.javatest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 本软件遵循Apache开源协议，所有版权归神州安付信息科技有限公司所有
 * @since 2022/12/15 16:19
 */
@SpringBootTest
public class ThreadPoolTest {
    /**
     * 线程池测试
     *
     */
    @Test
    public void test() {
        String s = "PLQCFa29a6/h5+crNIDWJLgc0hw5hgKs0OFY7fyOBMG3pOH/YnAfD8j6ZsvaqtPo1SV98nqSOW5PB9u8OWvDew==";
        //base64解码
        byte[] decode = Base64.getDecoder().decode(s);
        System.out.println(decode.length);
    }
}
