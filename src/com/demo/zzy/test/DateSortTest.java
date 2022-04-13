package com.demo.zzy.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/4/12 15:01
 */
public class DateSortTest {
    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = "2020-04-11 15:00:00";
        String date2 = "2020-04-12 15:00:00";
        String date3 = "2020-04-13 15:00:00";
        String date4 = "2020-04-14 15:00:00";
        Date parse1 = sdf.parse(date1);
        Date parse2 = sdf.parse(date2);
        Date parse3 = sdf.parse(date3);
        Date parse4 = sdf.parse(date4);

        List<Date> list = new ArrayList<Date>();
        list.add(parse1);
        list.add(parse2);
        list.add(parse3);
        list.add(parse4);
        System.out.println("排序前");
        System.out.println(list);

        list = list.stream().sorted(Comparator.comparing(Date::getTime, Comparator.reverseOrder())).collect(Collectors.toList());
        System.out.println("排序后");
        System.out.println(list);

        Map<String, Object> map = new HashMap<>();
        map.put("dates", list);
        System.out.println(map.get("dates"));

        List<Date> dateList = (List<Date>) map.get("dates");
        System.out.println(" 开始" + dateList);
        dateList = dateList.stream().sorted(Comparator.comparing(Date::getTime)).collect(Collectors.toList());
        System.out.println(" 结束" + dateList);
    }
}
