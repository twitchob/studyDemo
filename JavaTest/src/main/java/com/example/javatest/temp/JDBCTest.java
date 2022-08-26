package com.example.javatest.temp;

import java.io.PipedWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/8/25 9:30
 */
public class JDBCTest {
    public static void main(String[] args) throws  Exception{
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.3.111:3306/db1?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String psw = "123456";

        Class.forName(driver);

        Connection conn = DriverManager.getConnection(url, user, psw);
        PreparedStatement pstatement = conn.prepareStatement("select * from t1 ");
        ResultSet resultSet = pstatement.executeQuery();
        int columnCount = resultSet.getMetaData().getColumnCount();
        for (int i = 1; i < columnCount+1; i++) {
            System.out.println(resultSet.getMetaData().getColumnName(i));
        }


    }
}
