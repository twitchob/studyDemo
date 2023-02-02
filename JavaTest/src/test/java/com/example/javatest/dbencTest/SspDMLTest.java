package com.example.javatest.dbencTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/1/13 16:17
 */
@SpringBootTest
public class SspDMLTest extends SspBaseTest {

    /**
     * 测试insert
     *
     * @throws SQLException
     */
    @Test
    public void test1() throws SQLException {
        //INSERT INTO db1.zzy (u_id,u_name) VALUES (1 'zhangsan')
        //循环添加100条
        for (int i = 0; i < 100; i++) {
            String sql = "INSERT INTO db1.zzy (u_id,u_name) VALUES (" + i + ",'zhangsan" + i + "')";
            boolean execute = conn.createStatement().execute(sql);
            System.out.println(execute);
        }
    }

    /**
     * 测试update
     *
     * @throws SQLException
     */
    @Test
    public void test2() throws SQLException {
        //UPDATE db1.zzy SET u_name='lisi' WHERE u_id=1
        //循环更新u_id 0到99
        for (int i = 0; i < 100; i++) {
            String sql = "UPDATE db1.zzy SET u_name='lisi" + i + "' WHERE u_id=" + i;
            boolean execute = conn.createStatement().execute(sql);
            System.out.println(execute);
        }
    }

    /**
     * 测试delete
     *
     * @throws SQLException
     */
    @Test
    public void test3() throws SQLException {
        //DELETE FROM db1.zzy WHERE u_id=1
        //循环删除u_id 0到99
        for (int i = 0; i < 100; i++) {
            String sql = "DELETE FROM db1.zzy WHERE u_id=" + i;
            boolean execute = conn.createStatement().execute(sql);
            System.out.println(execute);
        }
    }


    /**
     * UPDATE t_sy_log_copy t SET t.opr_user_name = '1';
     */
    @Test
    public void test4() throws SQLException {
        conn.setAutoCommit(false);
        //UPDATE t_sy_log_copy t SET t.opr_user_name = '1';
        String sql = "UPDATE t_sy_log_copy t SET t.opr_user_name = '1'";
        boolean execute = conn.createStatement().execute(sql);
        //回滚
        conn.rollback();
    }

}