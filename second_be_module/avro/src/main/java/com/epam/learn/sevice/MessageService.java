package com.epam.learn.sevice;

import lombok.RequiredArgsConstructor;
import com.epam.learn.AvroMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    private final AvroKafkaProducer avroKafkaProducer;

    public void sendMessage(Message message) {
        log.info("New message: {}", message);
        var msg = mapToAvroObject(message);
        avroKafkaProducer.sendMessage(msg);
    }

    private AvroMessage mapToAvroObject(Message msg) {
        return AvroMessage.newBuilder()
                .setId(msg.getMessageId())
                .setText(msg.getMessageText())
                .setLocation(msg.getLocation())
                .build();
    }
}
