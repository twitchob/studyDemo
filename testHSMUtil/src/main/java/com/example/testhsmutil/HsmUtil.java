package com.example.testhsmutil;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * 
 * @author linzhj
 * @date 2023年1月12日
 */
public class HsmUtil {
    private static final Logger logger = LoggerFactory.getLogger(HsmUtil.class);
    private static final int SGD_SM4_ECB = 0x00000401;
    private static Pointer module;

    /**
     * 生成对称密钥
     * 
     * @param length 密钥长度
     * @return
     */
    public static byte[] random(int length) {
        byte[] random = new byte[length];
        int rv = HsmLib.INSTANCE.SDF_GenerateRandom(getSession(), length, random);
        logger.info("SDF_GenerateRandom====0x" + Integer.toHexString(rv));
        if (rv != 0) {
            throw new RuntimeException("生成随机数失败：0x" + Integer.toHexString(rv));
        }
        return random;
    }

    /**
     * SM4对称加密
     * 
     * @param key       密钥
     * @param plaintext 明文
     * @return
     */
    public static byte[] sm4Encrypt(byte[] key, byte[] plaintext) {
        Pointer session = getSession();
        PointerByReference keyPointer = new PointerByReference();
        int rv = HsmLib.INSTANCE.SDF_ImportKey(session, key, key.length, keyPointer);
        if (rv != 0) {
            HsmLib.INSTANCE.SDF_DestroyKey(session, keyPointer.getValue());
            throw new RuntimeException("导入明文会话密钥失败：0x" + Integer.toHexString(rv));
        }
        plaintext = padding(plaintext);
        byte[] ciphertext = new byte[plaintext.length];
        IntByReference ciphertextLength = new IntByReference();
        rv = HsmLib.INSTANCE.SDF_Encrypt(session, keyPointer.getValue(), SGD_SM4_ECB, null, plaintext, plaintext.length, ciphertext, ciphertextLength);
        HsmLib.INSTANCE.SDF_DestroyKey(session, keyPointer.getValue());
        if (rv != 0) {
            throw new RuntimeException("对称加密失败：0x" + Integer.toHexString(rv));
        }
        return ciphertext;
    }

    /**
     * SM4对称解密
     * 
     * @param key        密钥
     * @param ciphertext 密文
     * @return
     */
    public static byte[] sm4Decrypt(byte[] key, byte[] ciphertext) {
        Pointer session = getSession();
        PointerByReference keyPointer = new PointerByReference();
        int rv = HsmLib.INSTANCE.SDF_ImportKey(session, key, key.length, keyPointer);
        if (rv != 0) {
            HsmLib.INSTANCE.SDF_DestroyKey(session, keyPointer.getValue());
            throw new RuntimeException("导入明文会话密钥失败：0x" + Integer.toHexString(rv));
        }
        byte[] plaintext = new byte[ciphertext.length];
        IntByReference plaintextLength = new IntByReference();
        rv = HsmLib.INSTANCE.SDF_Decrypt(session, keyPointer.getValue(), SGD_SM4_ECB, null, ciphertext, ciphertext.length, plaintext, plaintextLength);
        HsmLib.INSTANCE.SDF_DestroyKey(session, keyPointer.getValue());
        if (rv != 0) {
            throw new RuntimeException("对称解密失败：0x" + Integer.toHexString(rv));
        }
        plaintext = cutting(plaintext);
        return plaintext;
    }
    
    
    private static byte[] padding(byte[] data) {
        int paddingNumber = 16 - (data.length % 16);
        byte[] paddingData = new byte[paddingNumber];
        for (int i = 0; i < paddingNumber; ++i) {
            paddingData[i] = (byte) paddingNumber;
        }
        byte[] outData = new byte[data.length + paddingNumber];
        System.arraycopy(data, 0, outData, 0, data.length);
        System.arraycopy(paddingData, 0, outData, data.length, paddingNumber);
        return outData;
    }

    private static byte[] cutting(byte[] data) {
        int paddingNumber = Byte.toUnsignedInt(data[data.length - 1]); 
        for (int i = 0; i < paddingNumber; ++i) {
            if ((int) data[data.length - paddingNumber + i] != paddingNumber) {
                return null;
            }
        }
        byte[] outData = new byte[data.length - paddingNumber];
        System.arraycopy(data, 0, outData, 0, data.length - paddingNumber);
        return outData;
    }

    private static synchronized Pointer getSession() {
        Pointer session = queue.poll();
        queue.add(session);
        return session;
    }

    private static Queue<Pointer> queue = new ConcurrentLinkedQueue<>();
    static {
        PointerByReference pointer = new PointerByReference();
        int rv = HsmLib.INSTANCE.SDF_OpenDevice(pointer);
        if (rv != 0) {
            logger.error("SDF_OpenDevice====0x" + Integer.toHexString(rv));
            throw new RuntimeException("打开密码模块会话句柄错误：0x" + Integer.toHexString(rv));
        }
        module = pointer.getValue();

        for (int i = 0; i < 20; i++) {
            rv = HsmLib.INSTANCE.SDF_OpenSession(module, pointer);
            if (rv != 0) {
                logger.error("SDF_OpenSession====0x" + Integer.toHexString(rv));
                throw new RuntimeException("打开密码模块错误：0x" + Integer.toHexString(rv));
            }
            queue.add(pointer.getValue());
        }
    }
}
