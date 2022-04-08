package com.demo.zzy.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/4/8 11:52
 */
public class StreamFilter {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        System.out.println(list);

        list = list.stream().filter(s -> !s.equals("1")).collect(Collectors.toList());
        System.out.println(list);
    }
    /**
     * Stream 过滤
     */
}
