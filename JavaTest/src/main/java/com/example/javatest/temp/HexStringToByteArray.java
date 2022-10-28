package com.example.javatest.temp;

import java.util.Arrays;

/**
 * @ClassName: HexStringToByteArray
 * @author: zhangzhongyuan@szanfu.cn
 * @date: 2022/10/27 0027 17:35
 * @Description: 测试fromHexString函数
 */
public class HexStringToByteArray {


    public static byte[] fromHexString(String var0) {
        char[] var1 = var0.toUpperCase().toCharArray();
        int var2 = 0;

        for(int var3 = 0; var3 < var1.length; ++var3) {
            if (var1[var3] >= '0' && var1[var3] <= '9' || var1[var3] >= 'A' && var1[var3] <= 'F') {
                ++var2;
            }
        }

        byte[] var6 = new byte[var2 + 1 >> 1];
        int var4 = var2 & 1;

        for(int var5 = 0; var5 < var1.length; ++var5) {
            if (var1[var5] >= '0' && var1[var5] <= '9') {
                var6[var4 >> 1] = (byte)(var6[var4 >> 1] << 4);
                var6[var4 >> 1] = (byte)(var6[var4 >> 1] | var1[var5] - 48);
            } else {
                if (var1[var5] < 'A' || var1[var5] > 'F') {
                    continue;
                }

                var6[var4 >> 1] = (byte)(var6[var4 >> 1] << 4);
                var6[var4 >> 1] = (byte)(var6[var4 >> 1] | var1[var5] - 65 + 10);
            }

            ++var4;
        }

        return var6;
    }
    public static void main(String[] args) {
        String s = "1234567887654321";
        byte[] bytes = s.getBytes();
        System.out.println(Arrays.toString(bytes));
    }
}
