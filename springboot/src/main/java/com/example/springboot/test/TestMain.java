package com.example.springboot.test;

import com.example.springboot.Bean.Person;
import com.example.springboot.Bean.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName: TestMain
 * @author: zhangzhongyuan@szanfu.cn
 * @date: 2022/11/2 0002 10:12
 * @Description: 程序入口
 */

@SpringBootTest(classes = TestMain.class)
public class TestMain {
    @Autowired
    private Student student;
    @Autowired
    Person person;


    @Test
    public void showBean() {
        System.out.println(student);
        System.out.println("======================================");
        System.out.println(person);
//        ApplicationContext context = new AnnotationConfigApplicationContext(DruidConfig.class);
//        Student student1 = (Student) context.getBean("Student");
//        System.out.println(student1);

    }
}
