package com.epam.learn.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataRepositoryTest {
    static UserEntity TESTED_USER = UserEntity.builder()
            .firstName("some-first-name")
            .lastName("some-last")
            .build();
    @Autowired
    DataRepository dataRepository;

    @Test
    void shouldAddNewUserToDbSuccessfully() {
        dataRepository.save(TESTED_USER);
        assertThat(dataRepository.findAll())
                .contains(TESTED_USER);
    }
}
