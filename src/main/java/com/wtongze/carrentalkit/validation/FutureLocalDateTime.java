package com.wtongze.carrentalkit.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FutureLocalDateTimeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FutureLocalDateTime {
    String message() default "Invalid LocalDateTime";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
