package others;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/4/26 14:53
 */
@ToString
@AllArgsConstructor
public class ToStringTest {
    private int length;
    private byte[] x;
    private byte[] y;

    public static void main(String[] args) {
        ToStringTest toStringTest = new ToStringTest(1, new byte[]{0x7f, 2, 3}, new byte[]{4, 5, 6});
        System.out.println(toStringTest);
    }
}
