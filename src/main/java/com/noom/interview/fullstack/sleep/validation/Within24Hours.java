package com.noom.interview.fullstack.sleep.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Within24HoursValidator.class)
public @interface Within24Hours {
    String message() default "startTime and dateTime must be within 24 hours of each other";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}