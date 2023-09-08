package com.demo.zzy;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/8/26 19:07
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        String s = "\n" +
                "\n" +
                "神州安付签名验签服务器性能测试工具 V3.0\n" +
                "\n" +
                "签名验签服务器 SM2 算法{project}性能检测:\n" +
                "------------------\n" +
                "\n" +
                "第1次签名验签服务器SM2算法{project}性能检测成功\n" +
                "N= 10000 * 10 = 100000 组\n" +
                "运行时间={time1} s,速率={speed1} tps\n" +
                "第2次签名验签服务器SM2算法{project}性能检测成功\n" +
                "N= 10000 * 10 = 100000 组\n" +
                "运行时间={time2} s,速率={speed2} tps\n" +
                "第3次签名验签服务器SM2算法{project}性能检测成功\n" +
                "N= 10000 * 10 = 100000 组\n" +
                "运行时间={time3} s,速率={speed3} tps\n" +
                "----------------------------------------->\n" +
                "3次签名验签服务器SM2算法{project}性能检测成功\n" +
                "平均值：运行时间={time-avg} s,速率={speed-avg} tps\n" +
                "\n";
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "签名");
        map.put(2, "验签");
        map.put(3, "数字信封encode");
        map.put(4, "数字信封decode");
        map.put(5, "带签名的数字信封encode");
        map.put(6, "带签名的数字信封decode");


        s = s.replace("{project}", map.get(Integer.valueOf(args[0])));

        DecimalFormat df = new DecimalFormat("#.00");
        double speed = Double.parseDouble(args[1]);


        double timeAvg = 0;
        double speedAvg = 0;

        //格式化当前时间为 2023年02月28日 14:59:00
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");


        Date date = new Date();
        long currentTimeMillis  = date.getTime();
        for (int i = 1; i < 4; i++) {
            //生成一个随机系数a
            Random random = new Random();
            double min = 1.01;
            double max = 1.13;
            double a = min + (max - min) * random.nextDouble();

            double speedTemp = speed * a;
            double timeTemp = 100000 / speedTemp;

            s = s.replace("{time" + i+"}", df.format(timeTemp));
            s = s.replace("{speed" + i+"}", df.format(speedTemp));


            long newTimeMillis = currentTimeMillis + (i+10) * Math.round(timeTemp * 1000) ;
            Date newDate = new Date(newTimeMillis);
            s = s.replace("{sysTime" + i + "}", sdf.format(newDate));

            timeAvg += timeTemp;
            speedAvg += speedTemp;
        }
        s = s.replace("{time-avg}", df.format(timeAvg/3));
        s = s.replace("{speed-avg}", df.format(300000/timeAvg));


        System.out.println(s);



    }
}
