package com.example.javatest.temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @ClassName JDBCBatch
 * @Description TODO
 * @Author 张忠源  zhangzhongyuan@szanfu.cn
 * @Date DATE{TIME}
 */
public class JDBCBatch {
    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://192.168.20.81:3308/db1?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root";
        String psw = "123456";

        Class.forName(driver);

        Connection conn = DriverManager.getConnection(url, user, psw);
        conn.setAutoCommit(false);
        Statement statement = conn.createStatement();
        for (int i = 0; i < 10; i++) {
            statement.addBatch("insert into t1  values ('"+i+"')");
        }

        int[] ints = statement.executeBatch();
        System.out.println(ints.length);
        conn.commit();
        conn.close();

    }
}
