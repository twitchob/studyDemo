package com.example.kafkatest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/7/12 23:26
 */
@RestController
@RequestMapping("kafka")
public class Producter {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("send")
    private String send(String message) {
        kafkaTemplate.send("test_topic", message);
        return "生产者发送消息成功";
    }

    private String sendM(String message) {
        kafkaTemplate.send("test_topic", message);
        return "生产者发送消息成功";
    }

    @RequestMapping("sendBymethod")
    private String sendBymethod(String message) {
        sendM(message);
        return "生产者通过调用方法发送消息成功！";
    }


}
