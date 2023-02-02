package com.example.javatest.dbencTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/1/9 10:44
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)  //must be static unless the test class  异常需要加这个注解
public class SsproxyTest  extends SspBaseTest {


    //测试sql
    //select * from user_test
    @Test
    public void test1() throws SQLException {
        ResultSet resultSet = conn.createStatement().executeQuery("select * from db1.user;");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
            System.out.println("===================================");
        }

    }




    /**
     * 修改加密规则  如果加密规则不存在会报错
     * ALTER ENCRYPT RULE t_encrypt (
     * COLUMNS(
     * (NAME=user_id,PLAIN=user_plain,CIPHER=user_cipher,ENCRYPT_ALGORITHM(TYPE(NAME='AES',PROPERTIES('aes-key-value'='123456abc'))))
     * ));
     */
    @Test
    public void test3() {
        try {
            String sql = "ALTER ENCRYPT RULE t_encrypt (\n" +
                    "    COLUMNS(\n" +
                    "        (NAME=user_id,PLAIN=user_plain,CIPHER=user_cipher,ENCRYPT_ALGORITHM(TYPE(NAME='AES',PROPERTIES('aes-key-value'='123abc'))))\n" +
                    "));";
            boolean execute = conn.createStatement().execute(sql);
            System.out.println(execute);
        } catch (SQLException e) {
            //如果加密规则不存在会报错 Encrypt rules `[t_encrypt]` do not exist in database `db1`.
            System.out.println(e.getMessage().equals("Encrypt rules `[t_encrypt]` do not exist in database `db1`."));//返回true
        }


    }

    /**
     * 创建加密规则
     * CREATE ENCRYPT RULE t_encrypt (
     * COLUMNS(
     * (NAME=user_id,PLAIN=user_plain,CIPHER=user_cipher,ENCRYPT_ALGORITHM(TYPE(NAME='AES',PROPERTIES('aes-key-value'='123456abc')))),
     * (NAME=order_id,PLAIN=order_plain,CIPHER =order_cipher,ENCRYPT_ALGORITHM(TYPE(NAME='RC4',PROPERTIES('rc4-key-value'='123456abc'))))
     * ));
     * <p>
     * 已经存在会报错 注意最后的句号 不能少
     * Duplicate encrypt rule names `[t_encrypt]` in database `db1`.
     */
    @Test
    public void test4() {
        try {
            String sql = "CREATE ENCRYPT RULE t_encrypt (\n" +
                    "    COLUMNS(\n" +
                    "        (NAME=user_id,PLAIN=user_plain,CIPHER=user_cipher,ENCRYPT_ALGORITHM(TYPE(NAME='AES',PROPERTIES('aes-key-value'='123abc')))),\n" +
                    "        (NAME=order_id,PLAIN=order_plain,CIPHER =order_cipher,ENCRYPT_ALGORITHM(TYPE(NAME='RC4',PROPERTIES('rc4-key-value'='123abc'))))\n" +
                    "));";
            Statement statement = conn.createStatement();
            boolean execute = statement.execute(sql);
            //如果true 获取结果集
            System.out.println("数量：" + statement.getUpdateCount());
        } catch (SQLException e) {
            System.out.println("报错" + e.getMessage());
            //如果加密规则已经存在会报错 Duplicate encrypt rule names `[t_encrypt]` in database `db1`.
            System.out.println(e.getMessage().equals("Duplicate encrypt rule names `[t_encrypt]` in database `db1`."));//返回true
        }
    }


    /**
     * 删除加密规则 如果加密规则不存在会报错 注意最后的句号 不能少
     * Encrypt rules `[user]` do not exist in database `db1`.
     *
     * @throws SQLException
     */
    @Test
    public void test2() {
        try {
            String sql = "DROP ENCRYPT RULE t_sy_user;";
            boolean execute = conn.createStatement().execute(sql);
            System.out.println(execute);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
