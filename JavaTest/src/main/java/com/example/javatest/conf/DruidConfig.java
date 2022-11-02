package com.example.javatest.conf;

import com.alibaba.druid.pool.DruidDataSource;
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
    @ConfigurationProperties(prefix = "spring.datasource")

    public DruidDataSource DruidDataSource() throws Exception {

//        Properties properties = new Properties();
//        properties.put("driverClassName", "com.mysql.cj.jdbc.Driver");
//        properties.put("url", "jdbc:mysql://192.168.10.80:3306/db_enc_tv?useUnicode=true&charecterEncoding=utf-8&serverTimezone=UTC");
//        properties.put("username", "root");
//        properties.put("password", "123456");
//        properties.put("initialSize", "10");
//        properties.put("maxActive", "20");
//        return DruidDataSourceFactory.createDataSource(properties);
        return new DruidDataSource();
    }


}
