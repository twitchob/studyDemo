package com.example.javatest.temp;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/8/26 10:09
 */
public class JDBCTsetOracle {

    public static void main(String[] args) throws Exception {

        String url = "jdbc:oracle:thin:@//192.168.1.201:1521/helowin";
        String user = "chsm";
        Connection conn = null;
        String password = "syswin#123";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
            if (null != conn) {
                System.out.println("连接数据库成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
