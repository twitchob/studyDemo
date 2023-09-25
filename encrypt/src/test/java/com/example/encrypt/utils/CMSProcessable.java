package com.example.encrypt.utils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/9/8 17:21
 */
public interface CMSProcessable {
    void write(OutputStream var1) throws IOException;

    Object getContent();
}
