package com.example.encrypt.asn1;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/9/11 14:13
 */
public interface GBObjectIdentifiers {
    ASN1ObjectIdentifier sm2 = new ASN1ObjectIdentifier("1.2.156.10197.1.301");
    ASN1ObjectIdentifier sm2_1 = sm2.branch("1");
    ASN1ObjectIdentifier sm2_2 = sm2.branch("2");
    ASN1ObjectIdentifier sm2_3 = sm2.branch("3");
    ASN1ObjectIdentifier gmalg = new ASN1ObjectIdentifier("1.2.156.10197.1");
    ASN1ObjectIdentifier sm3WithSM2Sign = gmalg.branch("501");
    ASN1ObjectIdentifier sha1WithSM2Sign = gmalg.branch("502");
    ASN1ObjectIdentifier sha256WithSM2Sign = gmalg.branch("503");
    ASN1ObjectIdentifier sm3WithRSASign = gmalg.branch("504");
    ASN1ObjectIdentifier sha1WithRSASign = gmalg.branch("505");
    ASN1ObjectIdentifier sha256WithRSASign = gmalg.branch("506");
    ASN1ObjectIdentifier sm1 = gmalg.branch("102");
    ASN1ObjectIdentifier sm4 = gmalg.branch("104");
    ASN1ObjectIdentifier sm6 = gmalg.branch("101");
    ASN1ObjectIdentifier sm7 = gmalg.branch("105");
    ASN1ObjectIdentifier sm8 = gmalg.branch("106");
    ASN1ObjectIdentifier ssf33 = gmalg.branch("103");
    ASN1ObjectIdentifier sm3 = gmalg.branch("401");
    ASN1ObjectIdentifier sm3_1 = sm3.branch("1");
    ASN1ObjectIdentifier sm3_2 = sm3.branch("2");
    ASN1ObjectIdentifier sm2_pkcs7 = new ASN1ObjectIdentifier("1.2.156.10197.6.1.4.2");
    ASN1ObjectIdentifier data = sm2_pkcs7.branch("1");
    ASN1ObjectIdentifier signedData = sm2_pkcs7.branch("2");
    ASN1ObjectIdentifier envelopedData = sm2_pkcs7.branch("3");
    ASN1ObjectIdentifier signedAndEnvelopedData = sm2_pkcs7.branch("4");
    ASN1ObjectIdentifier encryptedData = sm2_pkcs7.branch("5");
    ASN1ObjectIdentifier keyAgreementInfo = sm2_pkcs7.branch("6");
}
