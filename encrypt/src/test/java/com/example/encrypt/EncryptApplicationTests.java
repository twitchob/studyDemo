package com.example.encrypt;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class EncryptApplicationTests {

    @Test
    void contextLoads() {


        //hutool  des 算法 密钥64位

        //空字符串也进行加密


        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded();

        System.out.println(key.length);
        DES des = SecureUtil.des(key);

        //""  加解密测试
        byte[] encrypt = des.encrypt("".getBytes());
        System.out.println("cipher"+Arrays.toString(encrypt));
        byte[] decrypt = des.decrypt(encrypt);
        System.out.println("plaintext"+new  String(decrypt));
        Assert.isTrue(new String(decrypt).isEmpty());

        // "         " 加解密测试
        byte[] encrypt1 = des.encrypt(" ".getBytes());
        System.out.println("cipher长度:"+encrypt1.length+" cipher"+Arrays.toString(encrypt1));
        byte[] decrypt1 = des.decrypt(encrypt1);
        System.out.println("plaintext长度:"+decrypt1.length+" plaintext"+new  String(decrypt1));
        Assert.isTrue(new String(decrypt1).equals(" "));


    }


    @Test
    void test() {
        //hutool  des-ede 算法 密钥192位
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DESede.getValue()).getEncoded();
        System.out.println(key.length);
    }


    @Test
    void test1() {
        String s = "";
        String s1 = " ";
        String s2 = "  ";

        System.out.println("是否isEmpty");
        System.out.println(s.isEmpty());
        System.out.println(s1.isEmpty());
        System.out.println(s2.isEmpty());


        System.out.println("长度");
        System.out.println(s.length());
        System.out.println(s1.length());
        System.out.println(s2.length());

        System.out.println("字节数组长度");
        System.out.println(s.getBytes().length);
        System.out.println(s1.getBytes().length);
        System.out.println(s2.getBytes().length);

        System.out.println("字节数组");
        System.out.println(s.getBytes());
        System.out.println(s1.getBytes());
        System.out.println(s2.getBytes());

        System.out.println("字节数组Arrays.toString");
        System.out.println(Arrays.toString(s.getBytes()));
        System.out.println(Arrays.toString(s1.getBytes()));
        System.out.println(Arrays.toString(s2.getBytes()));

        System.out.println("new String(字节数组)");
        System.out.println(new String(s.getBytes()));
        System.out.println(new String(s1.getBytes()));
        System.out.println(new String(s2.getBytes()));

    }


    @Test
    void aes128() {
//        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
//        System.out.println(key.length);


        byte[] key = new byte[16];
        AES aes = new AES(key);
        byte[] encrypt = aes.encrypt("".getBytes());
        byte[] decrypt = aes.decrypt(encrypt);
        assert new String(decrypt).isEmpty();

    }

    @Test
    void aes192() {
        byte[] key = new byte[24];
        AES aes = new AES(key);
        byte[] encrypt = aes.encrypt("".getBytes());
        byte[] decrypt = aes.decrypt(encrypt);
        assert new String(decrypt).isEmpty();
    }

    @Test
    void testBase64Space() {
        String s  = " ";
        System.out.println(Base64.encode(s.getBytes()));
    }

}
