package com.project.employee.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
  private static Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
  
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;
  
  @Value("${jsa.kafka.topic}")
  String kafkaTopic;
  
  public void send(String data) {
      logger.info("sending data='{}'", data);      
      kafkaTemplate.send(kafkaTopic, data);
      
  }
}
