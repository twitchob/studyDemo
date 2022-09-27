package com.example.javatest.utils;

import org.ini4j.Ini;
import org.ini4j.Profile;
import org.ini4j.Wini;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class IniUtils {
    public static void main(String[] args) {
        try {
            readIni();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void readIni() throws Exception {
        Wini ini = new Wini(new File("C:\\Work\\learnPlace\\TestToolForZZY\\JavaTest\\src\\main\\resources\\evn.ini"));
        // 读
        Ini.Section section = ini.get("dev");
        String url = section.get("url");
        String user = section.get("user");
        String password = section.get("password");
        System.out.println(url);
        System.out.println(user);
        System.out.println(password);

        // or just use java.util.Map interface
        Map<String, String> map = ini.get("dev");
        String url1 = map.get("url");
        String user1 = map.get("user");
        String password1 = map.get("password");
        System.out.println(url1);
        System.out.println(user1);
        System.out.println(password1);

        // get all section names
        Set<String> sectionNames = ini.keySet();
        for (String sectionName : sectionNames) {
            Profile.Section section1 = ini.get(sectionName);
            System.out.println(sectionName);
        }

        // 写
        ini.put("sleepy", "age", 89);
        ini.put("sleepy", "weight", 45.6);
        ini.store();
    }
}
