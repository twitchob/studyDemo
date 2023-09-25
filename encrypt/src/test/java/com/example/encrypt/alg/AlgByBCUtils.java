package com.example.encrypt.alg;

import com.example.encrypt.asn1.JCESM2PrivateKey;
import org.bouncycastle.util.BigIntegers;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.*;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description 使用BC库实现需要的算法  SM3摘要 SM2签名验签 SM4加解密
 * @since 2023/9/11 15:41
 */
public class AlgByBCUtils {

    static Provider provider = new org.bouncycastle.jce.provider.BouncyCastleProvider();

    //BC库实现SM3摘要
    public static byte[] sm3Digest(byte[] data, PrivateKey privateKey) throws Exception {
        MessageDigest sm3 = MessageDigest.getInstance("SM3", provider);
        byte[] sigBytes = "1234567812345678".getBytes();
        BigInteger x = ((JCESM2PrivateKey) privateKey).getX();
        BigInteger y = ((JCESM2PrivateKey) privateKey).getY();
        byte[] bx = BigIntegers.asUnsignedByteArray(x);
        byte[] tmp = BigIntegers.asUnsignedByteArray(y);
        sigBytes = "1234567812345678".getBytes();
        byte[] input = new byte[bx.length + tmp.length + sigBytes.length];
        int offsetx = 0;
        System.arraycopy(bx, 0, input, offsetx, bx.length);
        int offset = offsetx + bx.length;
        System.arraycopy(tmp, 0, input, offset, tmp.length);
        offset += tmp.length;
        System.arraycopy(sigBytes, 0, input, offset, sigBytes.length);
        sm3.update(input);

        DigOutputStream digOutputStream = new DigOutputStream(sm3);
        digOutputStream.write(data);

        return sm3.digest();
    }

    static class DigOutputStream extends OutputStream {
        MessageDigest dig;

        public DigOutputStream(MessageDigest dig) {
            this.dig = dig;
        }

        public void write(byte[] b, int off, int len) throws IOException {
            this.dig.update(b, off, len);
        }

        public void write(int b) throws IOException {
            this.dig.update((byte) b);
        }
    }


    //BC库实现SM2签名
    public static byte[] sm2Sign(PrivateKey privateKey, byte[] data, SecureRandom random) throws Exception {
        Signature sm3withSM2 = Signature.getInstance("SM3withSM2", provider);
        sm3withSM2.initSign(privateKey, random);
        sm3withSM2.update(data);
        return  sm3withSM2.sign();  //SM2签名
    }

}

