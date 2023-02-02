package com.example.javatest.dbencTest;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/1/13 16:55
 */
public class SspDQLTest extends SspBaseTest {


    @Test
    public void testDQL() throws SQLException {
        String sql = "select * from zzy limit 10";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            //7colunms
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(4));
            System.out.println(rs.getString(5));
            System.out.println(rs.getString(6));
            System.out.println(rs.getString(7));
            //分隔符
            System.out.println("==================================");
        }
    }

    /**
     * 条件查询
     */
    @Test
    public void testDQL2() throws SQLException {
        String sql = "select * from zzy where u_id=1";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            //7colunms
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(4));
            System.out.println(rs.getString(5));
            System.out.println(rs.getString(6));
            System.out.println(rs.getString(7));
            //分隔符
            System.out.println("==================================");
        }
    }

    /**
     * 分组查询
     */
    @Test
    public void testDQL3() throws SQLException {
        String sql = "select u_id,count(*) from zzy group by u_id";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            //7colunms
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            //分隔符
            System.out.println("==================================");
        }
    }

    /**
     * 排序查询
     */
    @Test
    public void testDQL4() throws SQLException {
        String sql = "select * from zzy order by u_id desc";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            //7colunms
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(4));
            System.out.println(rs.getString(5));
            System.out.println(rs.getString(6));
            System.out.println(rs.getString(7));
            //分隔符
            System.out.println("==================================");
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void testDQL5() throws SQLException {
        String sql = "select * from zzy limit 10,10";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            //7colunms
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(4));
            System.out.println(rs.getString(5));
            System.out.println(rs.getString(6));
            System.out.println(rs.getString(7));
            //分隔符
            System.out.println("==================================");
        }
    }
   
}
