package com.epam.learn.service.utils;

import com.epam.learn.service.exception.DateTimeException;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@UtilityClass
public class Utils {
    public LocalDate parseDate(String dateString) {
        try {
            LocalDate parsedDate = LocalDate.parse(dateString);
            return parsedDate;
        } catch (DateTimeParseException e) {
            throw new DateTimeException("Invalid date format");
        }
    }
}
