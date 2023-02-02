package com.example.testhsmutil;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author zhangzhongyuan@szanfu.cn
 * @description
 * @since 2023/1/30 14:46
 */
@RequestMapping("/test")
@ResponseBody
@Controller
public class TestController {

    @RequestMapping("/test")
    public String test() {

        try {
            byte[] random = null;
            //循环1000次
            for (int i = 0; i < 10; i++) {
                //生成16字节的随机数
                random = HsmUtil.random(125000);
                //random 转化为16进制字符串
                StringBuilder randomStr = new StringBuilder();
                for (byte b : random) {
                    String hex = Integer.toHexString(b & 0xFF);
                    if (hex.length() == 1) {
                        hex = '0' + hex;
                    }
                    randomStr.append(hex.toUpperCase());
                }
                //hutool fileUtil 下载到根目录 /opt/db_encrypt/random.bin
                FileUtil.writeBytes(random, "/opt/zzytest/random/"+i+".bin");
                //清空randomStr
                randomStr.delete(0, randomStr.length());
            }
        } catch (IORuntimeException e) {
            return "fail";
        }

        return "success";
    }
}
