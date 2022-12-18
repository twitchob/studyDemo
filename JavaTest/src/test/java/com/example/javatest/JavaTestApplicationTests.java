package com.example.javatest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@SpringBootTest
class JavaTestApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * jdbc 连接时  数据类型测试
     */
    @Test
    void JDBCDataTypetest() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://192.168.3.221:3307/db1?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowMultiQueries=true&useCursorFetch=true";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");
        Connection conn = driver.connect(url, info);
        System.out.println("conn==" + conn);

        String sql = "select * from test_encrypt";
        ResultSet resultSet = conn.prepareStatement(sql).executeQuery();
        //遍历结果集 id
        while (resultSet.next()) {
            Object id = resultSet.getObject("id");
            Object t_name = resultSet.getObject("t_name");
            Object t_num = resultSet.getObject("t_num");
            Object t_gender = resultSet.getObject("t_gender");
            Object t_phone = resultSet.getObject("t_phone");
            Object t_card_num = resultSet.getObject("t_card_num");
            Object t_address = resultSet.getObject("t_address");
            Object t_date = resultSet.getObject("t_date");
            //分别获取各个字段的类型
            System.out.println("id==" + id + "  id.class==" + id.getClass());
            System.out.println("t_name==" + t_name + "  t_name.class==" + t_name.getClass());
            System.out.println("t_num==" + t_num + "  t_num.class==" + t_num.getClass());
            System.out.println("t_gender==" + t_gender + "  t_gender.class==" + t_gender.getClass());
            System.out.println("t_phone==" + t_phone + "  t_phone.class==" + t_phone.getClass());
            System.out.println("t_card_num==" + t_card_num + "  t_card_num.class==" + t_card_num.getClass());
            System.out.println("t_address==" + t_address + "  t_address.class==" + t_address.getClass());
            System.out.println("t_date==" + t_date + "  t_date.class==" + t_date.getClass());
            System.out.println("============================================");


        }
    }

    /**
     * jdbc 连接代理时  数据类型测试
     */
    @Test
    void JDBCDataTypetest2() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://192.168.3.221:3307/db1?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowMultiQueries=true&useCursorFetch=true";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");
        Connection conn = driver.connect(url, info);
        System.out.println("conn==" + conn);

    }
}
