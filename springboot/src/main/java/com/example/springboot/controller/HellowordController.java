package com.example.springboot.controller;

import com.example.springboot.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: HellowordController
 * @author: zhangzhongyuan@szanfu.cn
 * @date: 2022/11/2 0002 11:00
 * @Description:
 */
@Controller
@RequestMapping("/test")
public class HellowordController {
    @Autowired
    HelloWorldService helloWorldService;
    @RequestMapping("/test")
    @ResponseBody
    public String hello(){
        String hello = helloWorldService.getHello();
        return hello;
    }
}
