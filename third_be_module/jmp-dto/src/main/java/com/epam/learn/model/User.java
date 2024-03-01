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
@Table(name = "user_id")
public class User {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surName;
    @Column(name = "birthday")
    LocalDate birthday;
}
