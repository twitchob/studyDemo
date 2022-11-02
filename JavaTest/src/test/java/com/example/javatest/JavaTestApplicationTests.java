package com.example.javatest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
class JavaTestApplicationTests {
   @Autowired
   DataSource druidDataSource;

    @Test
    void contextLoads() throws SQLException {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DruidConfig.class);
//        DruidDataSource druidDataSource = (DruidDataSource) context.getBean("zzy");
//        Connection connection = druidDataSource.getConnection();
//        assert null != connection;
//        connection.prepareStatement("select  1 from dual");
        System.out.println(druidDataSource.toString());
        Connection connection = druidDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select  1 from dual");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
    }

}
