package com.epam.learn.service.api;

import com.epam.learn.dto.SubscriptionRequestDto;
import com.epam.learn.dto.SubscriptionResponseDto;

import java.util.List;


public interface SubscriptionService {
    SubscriptionResponseDto createSubscription(SubscriptionRequestDto request);

    SubscriptionResponseDto updateSubscription(SubscriptionRequestDto requestDto);

    SubscriptionResponseDto getSubscription(Long id);

    List<SubscriptionResponseDto> getAllSubscription();

    void deleteSubscription(Long id);
}
