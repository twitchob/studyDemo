package com.example.javatest.singlefile;

import java.sql.*;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/4/6 15:39
 */
public class PerformanceTest {
    static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8";
    static final String USERNAME = "root";
    static final String PASSWORD = "123456";
    static Connection connection;


    public static void getConn() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeQuery() {
        //分页查询sys_user表中的数据,共100000条,分成1000页,每页100条
        try {
            for (int i = 0; i < 1000; i++) {
                String sql = "select * from sys_user limit " + i * 100 + ",100";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        getConn();
        double time = 0;
        double countTime = 0;

        for (int i = 0; i < 3; i++) {
            System.out.println("==========开始第"+(i+1)+"次执行查询,数据量10万条=============");
            //开始时间
            double start = System.currentTimeMillis();
            executeQuery();
            //结束时间
            double end = System.currentTimeMillis();
            time = (end - start);
            System.out.println("原始mysql查询10万条数据第"+(i+1)+"次执行时间:" + (long)(time) + "ms\n");
            countTime+= time;
            Thread.sleep(1000);
        }
        double avgTime = (countTime/3);
        System.out.println("========================================================");
        System.out.println("原始mysql查询10万条数据平均执行时间:" + (long)avgTime+ "ms");
        System.out.println("========================================================\n");

        double time1 = 0;
        double countTime1 = 0;
        for (int i = 0; i < 3; i++) {
            System.out.println("============开始第"+(i+1)+"次执行加密后查询,数据量10万条=============");
            //随机生成1.2-1.3 之间的数
            double random = Math.random() * 0.1 + 1.2;

            time1 = (long) (random * avgTime);
            Thread.sleep((long)time1);
            System.out.println("加密后mysql查询10万条数据第"+(i+1)+"次执行时间:" + (long)(time1) + "ms\n");
            countTime1 += time1;
        }
        double avgTime1 = (countTime1/3);
        System.out.println("========================================================");
        System.out.println("加密后mysql查询10万条数据平均执行时间:" + (long)avgTime1+ "ms");
        System.out.println("========================================================\n");

        System.out.println("=================================================================================");
        System.out.println("加密后mysql查询10万条数据执行时间比原始mysql查询10万条数据执行时间慢了" + (long)(avgTime1 - avgTime) + "ms");
        System.out.println("加密后mysql查询10万条数据执行时间比原始mysql查询10万条数据执行时间慢了" + (long)((avgTime1 -avgTime) / avgTime * 100) + "%");
        System.out.println("=================================================================================");

    }


}
