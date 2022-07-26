package com.example.kafka2.proandcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/7/13 17:35
 */
@RestController
@RequestMapping("kafka")
public class Producter {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @RequestMapping("/test")
    private String send(String message) {
        System.out.println("message="+message);
        kafkaTemplate.send("topic_test", message);
        return "消息成功发送";
    }
}
