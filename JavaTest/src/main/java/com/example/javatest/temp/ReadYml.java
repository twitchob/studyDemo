package com.example.javatest.temp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/9/19 15:37
 */
@Controller
@RequestMapping("/test")
public class ReadYml {

    @Value("${spring.application.name}")
     String name;

    @ResponseBody
    @GetMapping("/name")
    public String list() {

      return name;
    }

}
