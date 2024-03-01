package com.epam.learn.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
    @Column(name = "startdate")
    LocalDate startDate;
}
