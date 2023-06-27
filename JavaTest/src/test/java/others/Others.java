package others;

import com.example.javatest.Color;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/3/31 15:44
 */
@SpringBootTest(classes = Others.class)
public class Others {
    @Test
    public void test() {
        Map<String, String> map = new HashMap<>();
        mapPut(map);
        System.out.println(map);
    }

    private void mapPut(Map<String, String> map) {
        map.put("1", "1");
        map.put("2", "2");
    }


    @Test
    public void test1() {
        String str = "B6765C6F6F159515BDCB25CCF50BB5502059313EBC42374A8CD69BA79B72A902";
        System.out.println(str.length());
        //随机生成一个指定长度的16进制字符串
        String randomStr = getRandomStr(64);
        System.out.println(randomStr);
        System.out.println(randomStr.length());
        System.out.println(Integer.toHexString(14));



    }

    private String getRandomStr(int i) {
        //随机生成一个指定长度的16进制字符串
        String randomStr = "";
        for (int j = 0; j < i; j++) {
            randomStr += Integer.toHexString((int) (Math.random() * 16));
        }
        return randomStr;
    }

    /**
     * 测试数字类型的  & 算法
     */
    @Test
    public void test2() {
        int i = 0xff;
        System.out.println(i);

        int i1 = 1234;

        int i2 = i1 & 0xff;
        System.out.println(i2);


    }
    @Test
    public void test3() {

        System.out.println(Color.SGD_FALSE);
    }

    @Test
    public void test4() {
        int i = 255;
        System.out.println(i);
        System.out.println(toHexString(i));
    }

    private String toHexString(int n)
    {
        String code = Integer.toHexString(n);

        for (int i = code.length(); i < 8; ++i) {
            code = "0" + code;
        }

        return "0x" + code;
    }


    @Test
    public void test5() {
        int ordinal = Color.RED.ordinal();
        System.out.println(ordinal);
    }


    @Test
    public void test6() {
        byte[] param = "1234567890".getBytes();
        new SM3

    }
}
