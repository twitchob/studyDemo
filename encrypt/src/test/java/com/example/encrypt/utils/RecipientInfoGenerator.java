package com.example.encrypt.utils;

import com.example.encrypt.asn1.RecipientInfo;

import javax.crypto.SecretKey;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.SecureRandom;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/9/8 17:58
 */
public interface RecipientInfoGenerator {

    RecipientInfo generate(SecretKey var1, SecureRandom var2, Provider var3) throws GeneralSecurityException;

    String getCipherOID();
}
