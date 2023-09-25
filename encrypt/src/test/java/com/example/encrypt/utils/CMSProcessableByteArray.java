package com.example.encrypt.utils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/9/8 17:21
 */
public class CMSProcessableByteArray implements CMSProcessable {
    private byte[] bytes;

    public CMSProcessableByteArray(byte[] bytes) {
        this.bytes = bytes;
    }

    public void write(OutputStream zOut) throws IOException {
        zOut.write(this.bytes);
    }

    public Object getContent() {
        return this.bytes.clone();
    }
}
