package com.example.springboot.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.springboot.Bean.Person;
import com.example.springboot.Bean.Student;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ClassName: DruidConfig
 * @author: zhangzhongyuan@szanfu.cn
 * @date: 2022/11/1 0001 14:35
 * @Description: durid配置类
 */


@Component
public class DruidConfig {

    @Bean
    public Student student() {
        Student student = new Student();
        student.setAge(18);
        student.setName("zhangzhongyuan");
        return student;
    }

    @Bean
    @ConfigurationProperties(prefix = "person")
    public Person person(){
        return new Person();
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.datasources")
    public DruidDataSource DruidDataSource(){
        return new DruidDataSource();
    }
}
