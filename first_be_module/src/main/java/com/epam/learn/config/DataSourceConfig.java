package com.epam.learn.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    @ConditionalOnProperty(
            name = "custom.datasource.enabled",
            havingValue = "true",
            matchIfMissing = true)
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        return DataSourceBuilder.create()
                .driverClassName(dataSourceProperties.determineDriverClassName())
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.determineUsername())
                .password(dataSourceProperties.getDataPassword())
                .build();
    }
}
