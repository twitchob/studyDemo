package com.example.javatest.temp;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/8/29 10:53
 */
public class DruidTest {
    public static void main(String[] args) throws Exception {


        //Properties 放入元素
        Properties properties = new Properties();
        properties.put("driverClassName", "oracle.jdbc.driver.OracleDriver");
        properties.put("url", "jdbc:oracle:thin:@192.168.1.201:1521:helowin");
        properties.put("username", "chsm");
        properties.put("password", "syswin#123");
        properties.put("initialSize", "10");
        properties.put("maxActive", "20");
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection conn = dataSource.getConnection();
        System.out.println(conn);

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from T_TEST");
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        System.out.println(columnCount);
        for (int i = 1; i <= columnCount; i++) {
            System.out.println(metaData.getColumnName(i));
        }

        while (resultSet.next()) {
            System.out.println("======开始=======");
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(resultSet.getString(metaData.getColumnName(i)));
            }
            System.out.println("=======结束=======\n\n\n\n");
        }

        conn.close();
    }
}
