package com.epam.learn.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id = "appInfo")
@RequiredArgsConstructor
public class AppInfoActuator {
    private final Environment env;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @ReadOperation
    public Map<String, String> getAppInfo() {
        String profiles =
                String.join(", ", env.getActiveProfiles());
        return Map.of(
                "PROFILE", profiles,
                "DB_URL", dbUrl
        );
    }

}
