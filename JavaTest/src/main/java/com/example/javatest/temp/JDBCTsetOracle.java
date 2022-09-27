package com.example.javatest.temp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/8/26 10:09
 */
public class JDBCTsetOracle {

    static String sql = "create or replace trigger\n" +
            "tg_enc_TEACHER\n" +
            "instead of insert or update or delete on TEACHER\n" +
            "for each row\n" +
            "\n" +
            "declare\n" +
            "  v_hsm_ip   varchar2(15);\n" +
            "  v_hsm_port binary_integer;\n" +
            "  v_key      char(16);\n" +
            "  v_key_len  binary_integer;\n" +
            "res_AGE  binary_integer;\n" +
            "v_out_AGE varchar2(1024);\n" +
            "v_out_len_AGE binary_integer;\n" +
            "res_NAME  binary_integer;\n" +
            "v_out_NAME varchar2(1024);\n" +
            "v_out_len_NAME binary_integer;\n" +
            "res_PHONE  binary_integer;\n" +
            "v_out_PHONE varchar2(1024);\n" +
            "v_out_len_PHONE binary_integer;\n" +
            "begin\n" +
            " v_hsm_ip   := '192.168.1.201';\n" +
            "v_hsm_port := 1521;\n" +
            "v_key      := '1234123412341234';\n" +
            "v_key_len  := 16;\n" +
            " if inserting then\n" +
            "res_AGE := db_enc(v_hsm_ip,\n" +
            "v_hsm_port,\n" +
            "v_key,\n" +
            "v_key_len,:new.AGE,\n" +
            "lengthb(:new.AGE),\n" +
            "v_out_AGE,\n" +
            "v_out_len_AGE);\n" +
            "res_NAME := db_enc(v_hsm_ip,\n" +
            "v_hsm_port,\n" +
            "v_key,\n" +
            "v_key_len,:new.NAME,\n" +
            "lengthb(:new.NAME),\n" +
            "v_out_NAME,\n" +
            "v_out_len_NAME);\n" +
            "res_PHONE := db_enc(v_hsm_ip,\n" +
            "v_hsm_port,\n" +
            "v_key,\n" +
            "v_key_len,:new.PHONE,\n" +
            "lengthb(:new.PHONE),\n" +
            "v_out_PHONE,\n" +
            "v_out_len_PHONE);\n" +
            "insert into TEACHER_$ENCRYPT$(ID,SEX,AGE,NAME,PHONE)\n" +
            "values (:new.ID,:new.SEX,v_out_AGE,v_out_NAME,v_out_PHONE);\n" +
            "elsif updating then res_AGE := db_enc(v_hsm_ip,\n" +
            "v_hsm_port,\n" +
            "v_key,\n" +
            "v_key_len,:new.AGE,\n" +
            "lengthb(:new.AGE),\n" +
            "v_out_AGE,\n" +
            "v_out_len_AGE);\n" +
            "res_NAME := db_enc(v_hsm_ip,\n" +
            "v_hsm_port,\n" +
            "v_key,\n" +
            "v_key_len,:new.NAME,\n" +
            "lengthb(:new.NAME),\n" +
            "v_out_NAME,\n" +
            "v_out_len_NAME);\n" +
            "res_PHONE := db_enc(v_hsm_ip,\n" +
            "v_hsm_port,\n" +
            "v_key,\n" +
            "v_key_len,:new.PHONE,\n" +
            "lengthb(:new.PHONE),\n" +
            "v_out_PHONE,\n" +
            "v_out_len_PHONE);\n" +
            "update\n" +
            "TEACHER_$ENCRYPT$ set\n" +
            "ID= :new.ID,SEX= :new.SEX,AGE = v_out_AGE,NAME = v_out_NAME,PHONE = v_out_PHONE where ID = :old.ID;\n" +
            " else\n" +
            "    delete from TEACHER_$ENCRYPT$ where ID = :old.ID;  end if;  dbms_output.put_line(res_AGE);  dbms_output.put_line(res_NAME);  dbms_output.put_line(res_PHONE);end;";

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
            assert conn != null;

           //conn.prepareStatement(sql).executeUpdate();
            conn.createStatement().executeUpdate(sql);
            System.out.println("成功");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
