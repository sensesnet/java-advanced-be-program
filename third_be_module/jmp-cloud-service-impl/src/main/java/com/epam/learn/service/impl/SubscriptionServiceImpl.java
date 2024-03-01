package com.epam.learn.service.impl;

import com.epam.learn.dto.SubscriptionRequestDto;
import com.epam.learn.dto.SubscriptionResponseDto;
import com.epam.learn.model.Subscription;
import com.epam.learn.model.User;
import com.epam.learn.service.api.SubscriptionService;
import com.epam.learn.service.exception.SubscriptionNotFoundException;
import com.epam.learn.service.exception.UserNotFoundException;
import com.epam.learn.service.repository.SubscriptionRepository;
import com.epam.learn.service.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public SubscriptionResponseDto createSubscription(@Valid SubscriptionRequestDto request) {
        Optional<User> user = userRepository.findById(request.getId());
        if (user.isPresent()) {
            Subscription subscription = Subscription.builder()
                    .user(user.get())
                    .startDate(LocalDate.now())
                    .build();
            subscription = subscriptionRepository.save(subscription);
            return toResponse(subscription);
        } else {
            throw new UserNotFoundException("The user not found by id:" + request.getUserId());
        }
    }

    @Override
    public SubscriptionResponseDto updateSubscription(@Valid SubscriptionRequestDto request) {
        Optional<Subscription> searchedSubscription = subscriptionRepository.findById(request.getId());
        Optional<User> searchedUser = userRepository.findById(request.getUserId());
        if (searchedSubscription.isPresent()) {
            if (searchedUser.isPresent()) {
                Subscription subscription = searchedSubscription.get();
                subscription.setId(searchedSubscription.get().getId());
                subscription.setUser(searchedUser.get());
                subscription.setStartDate(searchedSubscription.get().getStartDate());
                subscription = subscriptionRepository.save(subscription);
                return toResponse(subscription);
            } else {
                throw new UserNotFoundException("The user not found with ID: " + request.getUserId());
            }
        } else {
            throw new SubscriptionNotFoundException("The subscription not found with ID: " + request.getId());
        }
    }

    @Override
    public SubscriptionResponseDto getSubscription(@Valid Long id) {
        return subscriptionRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new UserNotFoundException(String.format(
                        "The subscription is not found by '%s' id.",
                        id)));
    }

    @Override
    public List<SubscriptionResponseDto> getAllSubscription() {
        return subscriptionRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSubscription(@Valid Long id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        subscription.ifPresent(value -> subscriptionRepository.delete(value));
    }

    private SubscriptionResponseDto toResponse(Subscription subscription) {
        return SubscriptionResponseDto.builder()
                .id(subscription.getId())
                .userId(subscription.getUser().getId())
                .startDate(subscription.getStartDate().toString())
                .build();
    }
}
