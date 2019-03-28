package com.project.employee.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.project.employee.storage.MessageStorage;

@Service
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    MessageStorage storage;
    
    @KafkaListener(topics = "${jsa.kafka.topic}")
    public void listen(@Payload String message) {
        logger.info("received message='{}'", message);
        Integer key = Integer.valueOf(message.substring(0, message.indexOf(":")));
        String value = message.substring(message.indexOf(":")+1);
        storage.put(key , value);
    }
    
}
