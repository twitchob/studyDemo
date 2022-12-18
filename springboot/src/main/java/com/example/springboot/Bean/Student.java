package com.example.springboot.Bean;

import lombok.Data;

/**
 * @ClassName: Student
 * @author: zhangzhongyuan@szanfu.cn
 * @date: 2022/11/2 0002 10:08
 * @Description: 测试 注入自定义bena到其他类中
 */

@Data
public class Student {
    private String name;
    private Integer age;
}
