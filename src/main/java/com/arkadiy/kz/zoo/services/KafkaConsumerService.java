package com.arkadiy.kz.zoo.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "animals", groupId = "group_id")
    public void consumeMessage(String message) {
        System.out.println(message);
    }
}
