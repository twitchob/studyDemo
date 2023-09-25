package com.example.encrypt;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.example.encrypt.alg.AlgByBCUtils;
import com.example.encrypt.asn1.*;
import com.example.encrypt.utils.CMSProcessable;
import com.example.encrypt.utils.CMSProcessableByteArray;
import com.example.encrypt.utils.CMSUtils;
import com.example.encrypt.utils.RecipientInfoGenerator;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.pkcs.ContentInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.internal.asn1.cms.CMSObjectIdentifiers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.*;

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
        System.out.println("cipher" + Arrays.toString(encrypt));
        byte[] decrypt = des.decrypt(encrypt);
        System.out.println("plaintext" + new String(decrypt));
        Assert.isTrue(new String(decrypt).isEmpty());

        // "         " 加解密测试
        byte[] encrypt1 = des.encrypt(" ".getBytes());
        System.out.println("cipher长度:" + encrypt1.length + " cipher" + Arrays.toString(encrypt1));
        byte[] decrypt1 = des.decrypt(encrypt1);
        System.out.println("plaintext长度:" + decrypt1.length + " plaintext" + new String(decrypt1));
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
        String s = " ";
        System.out.println(Base64.encode(s.getBytes()));
    }


    @Test
    void testEnvelopedData() throws Exception {
        System.out.println("数字信封编码");

        String encCertStr = "";
        String signCertStr = "";

        Certificate encCert = getCertByString(encCertStr);
        Certificate signCert = getCertByString(signCertStr);

        PrivateKey kmPrivateKey = null;

        ArrayList<Certificate> certificates = new ArrayList<>();
        certificates.add(signCert);


        byte[] data = "1234567890".getBytes();
        byte[] signedAndDevelopedData = signedAndEnvelopedData(signCert, kmPrivateKey, certificates,
                encCert, "SM4", data);

        ASN1Integer version = new ASN1Integer(0);

    }















    //=======================================================================
    PrivateKey priKey;
    byte[] message;
    SecureRandom rand = new SecureRandom();
    Map _digests = new HashMap();
    List<KeyTransRecipientInfoGenerator> recipientInfoGenerators = new ArrayList();
    protected List _certs;
    protected List _crls;


    //todo
    private byte[] signedAndEnvelopedData(Certificate signCert, PrivateKey priKey, ArrayList<Certificate> certChain, Certificate peerCert, String encAlg, byte[] message) throws Exception {
        //前置操作

        //添加接收者
        KeyTransRecipientInfoGenerator ktrig = new KeyTransRecipientInfoGenerator();
        ktrig.setRecipientCert((X509Certificate) peerCert);
        recipientInfoGenerators.add(ktrig);


        //添加签名者


        //添加证书撤销列表
        CollectionCertStoreParameters collectionCertStoreParameters = new CollectionCertStoreParameters(certChain);
        CertStore certs = CertStore.getInstance("Collection", collectionCertStoreParameters, "BC");
        this._certs.addAll(CMSUtils.getCertificatesFromStore(certs));
        this._crls.addAll(CMSUtils.getCRLsFromStore(certs));


        this.priKey = priKey;
        this.message = message;



        //todo 1 获取SM4加密提供程序和初始化一些变量
        //SM4 加密OID
        String encryptionOID = "SM4";
//        encryptionOID = "1.2.156.10197.1.104";
        KeyGenerator keyGen = KeyGenerator.getInstance("SM4", "BC"); // 获取密钥生成器
        keyGen.init(rand); // 初始化密钥生成器
        Provider encProvider = keyGen.getProvider(); // 获取加密提供程序
        ASN1EncodableVector recipientInfos = new ASN1EncodableVector(); // 用于存储接收者信息的ASN.1向量
        Cipher cipher = null; // 用于执行加密操作的Cipher对象

        AlgorithmIdentifier encAlgId; // 用于存储加密算法标识
        SecretKey encKey = null; // 用于存储加密密钥
        BEROctetString encContent; // 用于存储加密后的消息内容
        AlgorithmParameters params; // 用于存储加密算法参数
        //todo 2 初始化加密操作：
        try {
            cipher = Cipher.getInstance("SM4", "BC"); // 获取加密算法的Cipher对象
            encKey = keyGen.generateKey();
            params = AlgorithmParameterGenerator.getInstance(encryptionOID, encProvider).generateParameters();
            cipher.init(1, encKey, params, rand);
            if (params == null) {
                params = cipher.getParameters();
            }
            //todo 3 加密消息内容：
            encAlgId = this.getAlgorithmIdentifier(encryptionOID, params);
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            CipherOutputStream cOut = new CipherOutputStream(bOut, cipher);
            CMSProcessable content = new CMSProcessableByteArray(message);
            content.write(cOut);
            cOut.close();
            encContent = new BEROctetString(bOut.toByteArray());
        } catch (Exception e) {
            throw new Exception("exception decoding algorithm parameters.", e);
        }


        //todo 4 处理接收者信息：


        Iterator it = recipientInfoGenerators.iterator();
        boolean isGB = false;
        RecipientInfoGenerator recipient;
        Provider bc = CMSUtils.getProvider("BC");
        while (it.hasNext()) {
            recipient = (RecipientInfoGenerator) it.next();
            try {
                recipientInfos.add(recipient.generate(encKey, rand, bc));
            } catch (Exception e) {

            }
            if (!isGB && recipient.getCipherOID().startsWith("1.2.156.10197.1.301")) {
                isGB = true;
            }
        }

        ASN1ObjectIdentifier data;
        if (isGB) {
            data = new ASN1ObjectIdentifier("1.2.156.10197.6.1.4.2").branch("1");
        } else {
            data = CMSObjectIdentifiers.data;
        }
        //5 构建EncryptedContentInfo对象：
        EncryptedContentInfo eci = new EncryptedContentInfo(data, encAlgId, encContent);
        //6 初始化签名操作：

        try {
            cipher = Cipher.getInstance("SM4/CBC/NOPadding", bc);
            cipher.init(1, encKey, params, rand);
        } catch (Exception e) {
            throw new Exception("exception decoding algorithm parameters.", e);
        }
        //7 生成签名数据：
        CMSProcessable content = new CMSProcessableByteArray(message);
        SignedData signedData = generateSignedData(content, true, bc, cipher, signCert);
        //8 创建SignedAndEnvelopedData对象：
        SignedAndEnvelopedData contentInfo = new SignedAndEnvelopedData(signedData.getVersion(), new DERSet(recipientInfos),
                signedData.getDigestAlgorithms(), eci, signedData.getCertificates(), signedData.getCrls(), signedData.getSignerInfos());
        //9 将数据转换为ASN.1表示并返回：
        return contentInfo.toASN1Primitive().getEncoded("DER");



    }

    public SignedData generateSignedData(CMSProcessable content, boolean encapsulate, Provider sigProvider, Cipher cipher, Certificate signCert) throws NoSuchAlgorithmException {
        return generateSignedData(CMSObjectIdentifiers.data.getId(), content, encapsulate, sigProvider, cipher, signCert);
    }

    public SignedData generateSignedData(String eContentType, CMSProcessable content, boolean encapsulate, Provider sigProvider, Cipher cipher, Certificate signCert)
            throws NoSuchAlgorithmException {
        return generateSignedData(eContentType, content, encapsulate, sigProvider, false, cipher, signCert);
    }

    public SignedData generateSignedData(String eContentType, CMSProcessable content, boolean encapsulate, Provider sigProvider, boolean addDefaultAttributes, Cipher cipher, Certificate signCert)
            throws NoSuchAlgorithmException {

        ASN1EncodableVector digestAlgs = new ASN1EncodableVector();
        ASN1EncodableVector signerInfos = new ASN1EncodableVector();
        Map _digests = new HashMap();
        _digests.clear();
        List _signers = new ArrayList();


        boolean isCounterSignature = eContentType == null;
        ASN1ObjectIdentifier data = null;
        ASN1ObjectIdentifier signedData = null;
        ASN1ObjectIdentifier contentTypeOID = isCounterSignature ? CMSObjectIdentifiers.data : new ASN1ObjectIdentifier(eContentType);


        ASN1ObjectIdentifier encOid = GBObjectIdentifiers.sm3WithSM2Sign;   // 根据传入的私钥是SM2的 摘要算法是SM3  501
        ASN1ObjectIdentifier digestOid = GBObjectIdentifiers.sm3;   // 摘要算法固定

        data = GBObjectIdentifiers.data;
        contentTypeOID = data;

        try {

            //摘要
            byte[] sm3Hash = AlgByBCUtils.sm3Digest(message, priKey);
            _digests.put(digestOid, sm3Hash);
            //签名    SM2签名
            byte[] sm2Sign = AlgByBCUtils.sm2Sign(priKey, message, rand);
            digestAlgs.add(digestOid);
            //从签名证书中获取id
            SignerIdentifier signerIdentifier = CMSUtils.getSignerIdentifier((X509Certificate) signCert);
            AlgorithmIdentifier digAlgId = new AlgorithmIdentifier(digestOid, DERNull.INSTANCE);
            AlgorithmIdentifier encAlgId = new AlgorithmIdentifier(new ASN1ObjectIdentifier(encOid.getId()), DERNull.INSTANCE);
            SignerInfo signerInfo = new SignerInfo(signerIdentifier, digAlgId, null, encAlgId, new DEROctetString(sm2Sign), null);
            signerInfos.add(signerInfo);
        } catch (Exception e) {
            throw new RuntimeException("exception creating signature: " + e.getMessage(), e);
        }

        ASN1Set certificates = null;
        if (this._certs.size() != 0) {
            certificates = CMSUtils.createBerSetFromList(this._certs);
        }

        ASN1Set certrevlist = null;
        if (this._crls.size() != 0) {
            certrevlist = CMSUtils.createBerSetFromList(this._crls);
        }

        ASN1OctetString octs = null;
        if (encapsulate) {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            if (content != null) {
                try {
                    content.write(bOut);
                } catch (IOException var20) {
                    throw new RuntimeException(var20);
                }
            }

            octs = new BEROctetString(bOut.toByteArray());
        }

        ContentInfo encInfo = new ContentInfo(contentTypeOID, octs);
        SignedData sd = new SignedData(new DERSet(digestAlgs), encInfo, certificates, certrevlist, new DERSet(signerInfos));
        return sd;


    }


    //todo
    private Certificate getCertByString(String encCertStr) {
        return null;
    }

    protected AlgorithmIdentifier getAlgorithmIdentifier(String encryptionOID, AlgorithmParameters params) throws IOException {
        Object asn1Params;
        if (params != null) {
            asn1Params = ASN1Primitive.fromByteArray(params.getEncoded("ASN.1"));
        } else {
            asn1Params = DERNull.INSTANCE;
        }

        return new AlgorithmIdentifier(new ASN1ObjectIdentifier(encryptionOID), (ASN1Encodable) asn1Params);
    }


}
