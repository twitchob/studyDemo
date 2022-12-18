package com.example.javatest.temp;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 本软件遵循Apache开源协议，所有版权归神州安付信息科技有限公司所有
 * @since 2022/11/16 10:29
 */
public class JDBCStatementExecute {
    public static void main(String[] args) throws Exception {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://192.168.3.221:3307/db1?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowMultiQueries=true&useCursorFetch=true";
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","123456");
        Connection conn = driver.connect(url,info);
        Statement statement = conn.createStatement();
        for (int i = 0; i < 100; i++) {
            boolean execute = statement.execute( "SHOW VARIABLES LIKE 'lower_case_%';\n" +
                    "SHOW VARIABLES LIKE 'sql_mode';\n" +
                    "SELECT\n" +
                    "\tCOUNT(*) AS support_ndb \n" +
                    "FROM\n" +
                    "\tinformation_schema.ENGINES \n" +
                    "WHERE\n" +
                    "\tENGINE = 'ndbcluster'");
            System.out.println("execute==" + execute);
        }
        ResultSet resultSet = statement.getResultSet();
        System.out.println("resultSet=="+resultSet);

        System.out.println("conn=="+conn);

    }
}
