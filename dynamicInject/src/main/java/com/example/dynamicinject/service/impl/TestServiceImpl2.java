package com.example.dynamicinject.service.impl;

import com.example.dynamicinject.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2022/9/29 10:47
 */
@Service
public class TestServiceImpl2 implements TestService {

    @Override
    public String method1() {
        return "impl_2------method_1";
    }

    @Override
    public String method2() {
        return "impl_2------method_2";
    }
}
