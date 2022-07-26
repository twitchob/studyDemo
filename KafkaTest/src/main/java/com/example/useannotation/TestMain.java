package com.example.useannotation;

import java.lang.reflect.Method;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/7/22 10:10
 */
public class TestMain {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Class<? extends Calculator> aClass = calculator.getClass();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        StringBuilder log = new StringBuilder();
        int errorCount = 0;
        for (Method m : declaredMethods) {
            if (m.isAnnotationPresent(Check.class)) {
                try {
                    m.setAccessible(true);
                    m.invoke(calculator, null);
                } catch (Exception e) {
                    e.printStackTrace();
                    errorCount++;
                    log.append(m.getName());
                    log.append(" ");
                    log.append("has error:");
                    log.append("\n\r cause by:");
                    log.append("\n\r ");
                    //异常名字
                    log.append(e.getCause().getClass().getSimpleName());
                    log.append("\n\r ");
                    log.append("\n\r ");
                    //异常信息
                    log.append(e.getCause().getMessage());
                    log.append("\n\r ");
                    log.append("\n\r ");
                }
            }
        }
        log.append(aClass.getSimpleName());
        log.append(" has ");


        log.append(errorCount);
        log.append(" error.");
        log.append("\n\r");

        //生成测试报告
        System.out.println(log);

    }
}
