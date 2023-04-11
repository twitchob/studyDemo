package check;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/4/6 15:24
 */
@SpringBootTest(classes = CheckTest.class)
public class CheckTest {

   static final String URL= "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8";
   static final String USERNAME = "root";
   static final String PASSWORD = "123456";

    static Connection connection;
    @BeforeAll
    public static void beforeAll() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
             connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test() throws Exception{
        
        //开始时间
        long start = System.currentTimeMillis();
        executeQuery();
        //结束时间
        long end = System.currentTimeMillis();
        System.out.println("执行时间:" + (end - start) + "ms");
    }

    private static void executeQuery() throws SQLException {
        //分页查询sys_user表中的数据,共100000条,分成1000页,每页100条
        for (int i = 0; i < 1000; i++) {
            String sql = "select * from sys_user limit " + i * 100 + ",100";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            }
        }
    }
}
