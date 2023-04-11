package others;

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
}
