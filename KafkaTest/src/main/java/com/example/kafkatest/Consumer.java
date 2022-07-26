package com.example.kafkatest;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangzhongyuan@sinovatech.com
 * @description
 * @since 2022/7/12 23:26
 */
@Component
public class Consumer {
    @KafkaListener(topics = "test_topic")
    public void listen(ConsumerRecord<?,?> record) throws Exception{


        System.out.printf("topic = %s ,offset = %d,value = %s \n",record.topic(),record.offset(),record.value());
    }
}
