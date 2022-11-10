package com.example.af_interface_call.others;

import java.util.Base64;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/11/10 11:28
 */
public class Base64Test {
    public static void main(String[] args) {
        String src = "g18on2bwmF8=";
        //base64转化为字节数组
        byte[] decode = Base64.getDecoder().decode(src);
        //转16进制
        String s = toHex(decode);
        System.out.println(s);
    }
    public static String toHex(byte[] data) {
        char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        final int len = data.length;
        final char[] out = new char[len << 1];// len*2
        for (int i = 0, j = 0; i < len; i++) {
            out[j++] = digits[(0xF0 & data[i]) >>> 4];// 高位
            out[j++] = digits[0x0F & data[i]];// 低位
        }
        return new String(out);
    }
}
