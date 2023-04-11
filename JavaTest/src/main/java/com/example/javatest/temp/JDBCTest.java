package com.example.javatest.temp;

import java.io.PipedWriter;
import java.sql.*;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/8/25 9:30
 */
public class JDBCTest {
    public static void main(String[] args) throws  Exception{
        String driver = "com.mysql.cj.jdbc.Driver";
       // String url = "jdbc:mysql://192.168.20.81:3308/db1?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&allowMultiQueries=true";
        String url = "jdbc:mysql://192.168.3.140:3306/test?characterEncoding=utf8&useSSL=false&useTimezone=true&serverTimezone=GMT%2B8";
        String user = "root";
        String psw = "123456";

        Class.forName(driver);

        Connection conn = DriverManager.getConnection(url, user, psw);
//        PreparedStatement pstatement = conn.prepareStatement("select * from t1 ");
//        ResultSet resultSet = pstatement.executeQuery();
//        int columnCount = resultSet.getMetaData().getColumnCount();
//        for (int i = 1; i < columnCount+1; i++) {
//            System.out.println(resultSet.getMetaData().getColumnName(i));
//        }

//        Statement statement = conn.createStatement();
//        boolean execute = statement.execute("SHOW VARIABLES LIKE 'lower_case_%'; SHOW VARIABLES LIKE 'sql_mode'; SELECT COUNT(*) AS support_ndb FROM information_schema.ENGINES WHERE Engine = 'ndbcluster'"
//                , 2);
//        System.out.println(execute);

//        Statement statement = conn.createStatement();
//        ResultSet resultSet = statement.executeQuery("SELECT SCHEMA_NAME, DEFAULT_CHARACTER_SET_NAME, DEFAULT_COLLATION_NAME FROM information_schema.SCHEMATA");
//        while (resultSet.next()){
//            System.out.println(resultSet.getString(1));
//            System.out.println(resultSet.getString(2));
//            System.out.println(resultSet.getString(3));
//        }

//              Statement statement = conn.createStatement();
//              //select * from user_test
//                ResultSet resultSet = statement.executeQuery("select * from user_test");
//                while (resultSet.next()){
//                    System.out.println(resultSet.getString(1));
//                    System.out.println(resultSet.getString(2));
//                    System.out.println(resultSet.getString(3));
//                    System.out.println(resultSet.getString(4));
//                    System.out.println(resultSet.getString(5));
//                    System.out.println(resultSet.getString(6));
//                    System.out.println(resultSet.getString(7));
//                    System.out.println("===================================");
//                }
//

    }
}
