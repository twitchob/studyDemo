package com.example.springboot.service;

import com.example.springboot.Bean.Person;
import com.example.springboot.Bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: HelloWorldService
 * @author: zhangzhongyuan@szanfu.cn
 * @date: 2022/11/2 0002 11:20
 * @Description:
 */
@Service
public class HelloWorldService {
    @Autowired
    Person person;
    @Autowired
    Student student;
    public String getHello() {
        return person.toString()+"==="+student.toString();
    }
}
