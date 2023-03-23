package com.example.javatest;

import com.alibaba.druid.sql.dialect.mysql.parser.MySqlLexer;
import com.example.javatest.antlr.calculator.AntlrVistor;
import com.example.javatest.antlr.calculator.CalculatorLexer;
import com.example.javatest.antlr.calculator.CalculatorParser;
import com.example.javatest.antlr.hello.HelloLexer;
import com.example.javatest.antlr.hello.HelloParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName AntlrTest
 * @Description 单元测试
 * @Author 张忠源  zhangzhongyuan@szanfu.cn
 * @Date DATE{TIME}
 */
@SpringBootTest
public class AntlrTest {


    @Test
    public void antlrTest() {
        //词法分析
        HelloLexer lexer = new HelloLexer(CharStreams.fromString("hello worrd"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HelloParser parser = new HelloParser(tokens);
        ParseTree tree = parser.r();
        System.out.println(tree.toStringTree(parser));

    }

    /**
     * 测试antlr4计算器
     */
    @Test
    public void testCalculator() {
        //lexer
        CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString("1+2*3"));
        //token
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        //parser
        CalculatorParser parser = new CalculatorParser(tokens);
        //调用规则
        ParseTree tree = parser.expr();
        AntlrVistor antlrVistor = new AntlrVistor();
        Integer result = antlrVistor.visit(tree);
        System.out.println("result = " + result);

    }


    @Test
    public void testMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "11");
        map.put("2", "22");
        map.put("3", "33");


        System.out.println("map = " + map);
        Collection<String> values = map.values();
        System.out.println("values = " + values);

        Set<Map.Entry<String, String>> entries = map.entrySet();
        System.out.println("entries = " + entries);

        for (Map.Entry<String, String> entry : entries) {
            System.out.println("entry.getvalue = " + entry.getValue());
        }


    }


}
