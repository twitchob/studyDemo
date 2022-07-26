package com.example.annotation;

import com.example.annotation2.Fieldkuang;

import java.lang.annotation.Annotation;

/**
 * @author zzy
 * @description
 * @since 2022/7/21 18:35
 */

@Person()
public class SuperMan {
    @Fieldkuang(columnName = "name", type = "String", length = 100)
    public String name;
}
