package com.example.encrypt.desaes;


import cn.hutool.core.codec.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/9/28 14:57
 */
public class DesAesDemo {
    public static void main(String[] args) throws Exception {
        //原文
        String input = "神州安付";
        //密钥
        String key = "12345678";
        //加密方式
        String transformation = "DES";
        String encrypt = encrypt(input, key, transformation);
        System.out.println("加密后的密文：" + encrypt);

    }

    /**
     * 加密
     *
     * @param input          明文
     * @param key            密钥 DES只能是8字节64位长度密钥
     * @param transformation 加密方式
     */
    private static String encrypt(String input, String key, String transformation) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //创建加密对象
        Cipher des = Cipher.getInstance(transformation);
        //加密规则  params 密钥 加密方式
        SecretKeySpec rule = new SecretKeySpec(key.getBytes(), transformation);
        //加密初始化
        des.init(Cipher.ENCRYPT_MODE, rule);
        //加密
        byte[] bytes = des.doFinal(input.getBytes(StandardCharsets.UTF_8));
        //编码 Base64
        return Base64.encode(bytes);
    }
}
