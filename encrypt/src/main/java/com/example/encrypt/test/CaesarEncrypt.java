package com.example.encrypt.test;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/9/27 16:23
 */
public class CaesarEncrypt {
    public static void main(String[] args) {
        String input = "hello world";

        //原文右移三位
        int key = 3;
        String extracted = encrypt(input, key);
        System.out.println("密文: "+extracted);

        String decrypt = decrypt(key, extracted);
        System.out.println("明文: " + decrypt);

    }

    /**
     * 解密
     */
    private static String decrypt(int key, String extracted) {
        char[] chars = extracted.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            aChar -= key;
            sb.append(aChar);
        }
        return sb.toString();
    }

    /**
     * 加密
     */
    private static String encrypt(String input, int key) {
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            aChar += key;
            sb.append(aChar);
        }
        return sb.toString();
    }
}
