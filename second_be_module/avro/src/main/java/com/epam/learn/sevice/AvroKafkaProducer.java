package com.epam.learn.sevice;

import com.epam.learn.AvroMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaFailureCallback;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AvroKafkaProducer {
    private final KafkaTemplate<String, AvroMessage> messageKafkaTemplate;

    @Value("${spring.kafka.producer.topics.message}")
    private String messageTopic;

    public void sendMessage(AvroMessage message) {
        ListenableFuture<SendResult<String, AvroMessage>> future = messageKafkaTemplate
                .send(messageTopic, UUID.randomUUID().toString(), message);
        future.addCallback(this::onSuccess, (KafkaFailureCallback<String, Message>) this::onError);
    }

    private void onSuccess(SendResult<String, AvroMessage> result) {
        ProducerRecord<String, AvroMessage> producerRecord = result.getProducerRecord();
        log.info("Producer successfully sent to message kafka topic: key = {}",
                producerRecord.key());
    }

    private void onError(KafkaProducerException ex) {
        log.error("Producer failed to send message to message kafka topic, failure: {}",
                ex.getFailedProducerRecord());
    }

}

