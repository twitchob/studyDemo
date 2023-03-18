package JDBC;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/3/17 10:36
 */
@SpringBootTest(classes = JdbcTest.class)
public class JdbcTest {
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:13307/db1?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&allowMultiQueries=true";
    static String user = "root";
    static String psw = "root";

    @BeforeAll
    public static void init() throws Exception {
        Class.forName(driver);
    }

    @Test
    public void test() throws Exception {

        Connection conn = DriverManager.getConnection(url, user, psw);
        Statement statement = conn.createStatement();
        //select * from user_test
        ResultSet resultSet = statement.executeQuery("select * from t_encrypt");
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));

            System.out.println("===================================");
        }
    }
}
