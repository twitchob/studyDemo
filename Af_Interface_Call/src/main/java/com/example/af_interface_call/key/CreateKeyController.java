//package com.example.af_interface_call.key;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Base64;
//
///**
// * @author zhangzhongyuan@szanfu.cn
// * @description
// * @since 2022/11/8 16:36
// */
//public class CreateKeyController {
//
//    private final static String CHARSET = "UTF-8";
//    /** 签发时间 */
//    public static int STF_TIME_OF_STAMP = 1;
//    /** 签发者的通用名 */
//    public static int STF_CN_OF_TSSIGNER = 2;
//    /** 时间戳请求的原始信息的HASH值 */
//    public static int STF_ORINGINAL_DATA = 3;
//    /** 时间戳服务器的证书 */
//    public static int STF_CERT_OF_TSSERVER = 4;
//    /** 时间戳服务器的证书链 */
//    public static int STF_CERTCHAIN_OF_TSSERVER = 5;
//    /** 时间源的来源 */
//    public static int STF_SOURCE_OF_TIME = 6;
//    /** 时间精度 */
//    public static int STF_TIME_PRECISION = 7;
//    /** 响应方式 */
//    public static int STF_RESPONSE_TYPE = 8;
//    /** 签发者国家 */
//    public static int STF_SUBJECT_COUNTRY_OF_TSSIGNER = 9;
//    /** 签发者组织 */
//    public static int STF_SUBJECT_ORGNIZATION_OF_TSSIGNER = 10;
//    /** 签发者城市 */
//    public static int STF_SUBJECT_CITY_OF_TSSIGNER = 11;
//    /** 签发者电子邮箱 */
//    public static int STF_SUBJECT_EMAIL_OF_TSSIGNER = 12;
//
//    public static void main(String[] args) throws Exception {
//        byte[] plaintext = "hello/密码服务接口测试".getBytes(CHARSET); // 明文
//        byte[] iv_8 = "01234567".getBytes(CHARSET); // 8字节初始化向量
//        byte[] iv_16 = "0123456789ABCDEF".getBytes(CHARSET); // 16字节初始化向量
//        byte[] publicKey = Base64.getDecoder().decode("jzKwrUeP3gWy2n2YwvfilDRPuCY6q3kZJgPgnXcYrqwprJShs6SEzrlL4LfXtAGtAAFrtjpfTNUJkvZMgcEtJQ=="); // 外部SM2公钥
//        byte[] privateKey = Base64.getDecoder().decode("NQ9JYqo/4kVraSvItRbDgDemV4RCBqK/ZgInyKoJYYQ="); // 外部SM2私钥
//        String ciphertext = null; // 密文
//
//        System.out.println("创建对称密钥...");
//        String keyId_symm = KspKmsApi.create("SYMM_128", "01");// 创建128位对称密钥
//        System.out.println("创建SM2密钥...");
//        String keyId_sm2 = KspKmsApi.create("SM2_256", "01"); // 创建SM2密钥
//        System.out.println("创建RSA密钥...");
//        String keyId_rsa = KspKmsApi.create("RSA_2048", "01"); // 创建2048位RSA密钥
//    }
//}
