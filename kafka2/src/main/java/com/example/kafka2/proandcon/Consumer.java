package com.example.kafka2.proandcon;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/7/13 17:40
 */
@Component
public class Consumer {
    @KafkaListener(topics="topic_test")
    public void listen(ConsumerRecord<?,?> record) {
        System.out.println("topic="+record.topic());
        System.out.println("offset="+record.offset());
        System.out.println("value="+record.value());
    }
}
