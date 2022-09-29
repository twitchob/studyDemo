package com.example.dynamicinject.controller;

import com.example.dynamicinject.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/9/29 10:47
 */
@Controller
public class TestController {

    @Autowired
    TestService testService;

   public void test() {
        System.out.println(testService.method1());
    }

}
