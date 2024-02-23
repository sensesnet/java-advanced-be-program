package com.epam.learn.controller;


import com.epam.learn.sevice.Message;
import com.epam.learn.sevice.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AvroMessageController {
    private final MessageService messageService;

    @PostMapping("/msg")
    public void addMsg(@RequestBody Message message) {
        messageService.sendMessage(message);
    }
}
