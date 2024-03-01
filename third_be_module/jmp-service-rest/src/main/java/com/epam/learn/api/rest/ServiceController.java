package com.epam.learn.api.rest;

import com.epam.learn.dto.SubscriptionRequestDto;
import com.epam.learn.dto.SubscriptionResponseDto;
import com.epam.learn.service.api.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/subscription")
public class ServiceController {
    @Autowired
    SubscriptionService subscriptionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriptionResponseDto createSubscription(@Valid @RequestBody SubscriptionRequestDto request){
        return subscriptionService.createSubscription(request);
    }

    @PutMapping
    public SubscriptionResponseDto updateSubscription(@Valid @RequestBody SubscriptionRequestDto request) {
        return subscriptionService.updateSubscription(request);
    }

    @GetMapping
    public List<SubscriptionResponseDto> getAllSubscription() {
        return subscriptionService.getAllSubscription();
    }

    @DeleteMapping("/{id}")
    public void deleteSubscription(@PathVariable("id") Long id) {
        subscriptionService.deleteSubscription(id);
    }

    @GetMapping("/{id}")
    public SubscriptionResponseDto getSubscription(@PathVariable("id") Long id) {
        return subscriptionService.getSubscription(id);
    }
}
