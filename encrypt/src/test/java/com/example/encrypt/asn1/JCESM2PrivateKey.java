package com.example.encrypt.asn1;

import lombok.Getter;

import java.math.BigInteger;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/9/11 16:35
 */
@Getter
public class JCESM2PrivateKey {
    static final long serialVersionUID = -8145849727580266753L;
    private static BigInteger MAX_KEY_INDEX = new BigInteger("100");
    private int keyIndex;
    private int keyType;
    private BigInteger s;
    private BigInteger x;
    private BigInteger y;
}
