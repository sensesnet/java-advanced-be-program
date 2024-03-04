package com.epam.learn.model;

import java.time.LocalDateTime;

public record UserAuthAttempts(int attempts, LocalDateTime blockTimestamp) {
}
