package com.epam.learn.sevice;

import com.epam.learn.AvroMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class AvroKafkaConsumer {
    @KafkaListener(topics = "${spring.kafka.producer.topics.message}")
    public void listenMessageTopic(ConsumerRecord<String, AvroMessage> consumerRecord) {
        log.info("Consumer received message: {}", consumerRecord.value());
    }
}
