package com.demo.zzy.concurrency;

import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/4/13 16:51
 */
public class MyRunnable  {
    public static void main(String[] args) {
        ///new map
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("1","2");
        System.out.println(map.get("1"));
    }
}
