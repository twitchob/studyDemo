package com.example.javatest;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName AntlrTest
 * @Description 单元测试
 * @Author 张忠源  zhangzhongyuan@szanfu.cn
 * @Date DATE{TIME}
 */
@SpringBootTest
public class AntlrTest {

    /**
     * 测试
     */
    @Test
    public void testAntlr() {
        //定义字符串数组
        String[] testStr = {
                "Hello world",
                "hello world",
                "hi world"
        };
        //循环遍历字符串数组
        for (String str : testStr) {
            System.out.println("Input: " + str);
            run(str);
        }

    }

    public void run(String str) {

        ANTLRInputStream input = new ANTLRInputStream(str);
        //词法分析器
        hello.HelloLexer lexer = new hello.HelloLexer(input);
        //词法分析器的token流
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        //语法分析器
        hello.HelloParser parser = new hello.HelloParser(tokens);
        //语法分析器的规则
        parser.r();

    }
}
