package com.example.javatest.dbencTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/1/13 16:17
 */
@SpringBootTest
public class SspBaseTest {
    static Connection conn = null;

    static Statement stmt = null;
    static ResultSet rs = null;
    @BeforeAll
    public static void beforeAll() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        //String url = "jdbc:mysql://192.168.20.81:3308/db1?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&allowMultiQueries=true";
        String url = "jdbc:mysql://localhost:13307/db1?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
       // String psw = "123456";
        String psw = "root";

        Class.forName(driver);

        conn = DriverManager.getConnection(url, user, psw);
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }

    }
}
