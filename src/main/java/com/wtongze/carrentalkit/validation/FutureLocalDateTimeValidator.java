package com.wtongze.carrentalkit.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Arrays;
import java.util.List;

public class FutureLocalDateTimeValidator implements ConstraintValidator<FutureLocalDateTime, LocalDateTime> {
    private final List<Integer> validMinutes = Arrays.asList(0, 30);

    @Override
    public void initialize(FutureLocalDateTime constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDateTime dateTime, ConstraintValidatorContext context) {
        return dateTime != null &&
                !dateTime.isBefore(LocalDateTime.now()) &&
                validMinutes.contains(dateTime.getMinute());
    }
}
